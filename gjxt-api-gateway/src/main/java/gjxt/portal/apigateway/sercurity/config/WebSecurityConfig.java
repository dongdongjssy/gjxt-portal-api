package gjxt.portal.apigateway.sercurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.Arrays;
import java.util.Collections;

/**
 * Author: goose
 * createAt: 2019/4/1
 */
@Configuration
@EnableWebFluxSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig{

    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    private final ServerAccessDeniedHandler accessDeniedHandler;

    private final UserDetailsService customUserDetailsService;

//    private final JwtAuthenticationTokenFilter authenticationTokenFilter;
    @Autowired
    ScSecurityContextRepository scSecurityContextRepository;

    @Autowired
    ScAuthenticationManager scAuthenticationManager;

    @Autowired
    ScAuthorizationManager scAuthorizationManager;





    @Autowired
    public WebSecurityConfig(JwtAuthenticationEntryPoint unauthorizedHandler,
                             @Qualifier("RestAuthenticationAccessDeniedHandler") ServerAccessDeniedHandler accessDeniedHandler,
                             @Qualifier("CustomUserDetailsService") UserDetailsService customUserDetailsService
            /*, JwtAuthenticationTokenFilter authenticationTokenFilter*/) {
        this.unauthorizedHandler = unauthorizedHandler;
        this.accessDeniedHandler = accessDeniedHandler;
        this.customUserDetailsService = customUserDetailsService;
//        this.authenticationTokenFilter=authenticationTokenFilter;
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置UserDetailsService
                .userDetailsService(this.customUserDetailsService)
                // 使用BCrypt进行密码的hash
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 装载BCrypt密码编码器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置方式要换成 WebFlux的方式
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity.cors().configurationSource(corsConfigurationSource()).and().csrf().disable()
                 .securityContextRepository(scSecurityContextRepository) //存储认证信息
                .authenticationManager(scAuthenticationManager) //认证管理
                .authorizeExchange()// 请求拦截处理
                        .pathMatchers("/**/sign","/**/login","/**/getImageVerifyCode","/**/validateImageVerifyCode",
                                "/**/map_src/**","/**/webSocket/**","/**/api/mostAskedQuestions/**","/**/api/announcement/**",
                                "/**/api/gjxtAnnouncementMedia/**","/**/api/carousel/**","/**/api/messageBoard/list","/**/api/messageBoard/getById/**","/**/api/gjxtMultimedia/**",
                                "/**/api/policyExplanation/**","/**/api/policyFile/**","/**/api/workUpdate/**","/**/api/clientUser/**",
                                "/**/api/certificate/**","/**/api/home/scholarshipList")
//                        .pathMatchers("/*")
                        .permitAll()
                        .anyExchange().access(scAuthorizationManager)
                .and() //权限
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler) //权限认证失败
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

        // 添加JWT filter
        httpSecurity
                .addFilterAfter(new ScFilter(), SecurityWebFiltersOrder.AUTHORIZATION); //拦截处理
        return httpSecurity.build();
    }

    // 跨域配置
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource (new PathPatternParser ());
        CorsConfiguration corsConfig = new CorsConfiguration ();

        // 允许所有请求方法
        corsConfig.addAllowedMethod ("*");
        // 允许所有域，当请求头
        corsConfig.addAllowedOrigin ("*");
        // 允许全部请求头
        corsConfig.addAllowedHeader ("*");
        // 允许携带 Authorization 头
        corsConfig.setAllowCredentials (true);
        // 允许全部请求路径
        source.registerCorsConfiguration ("/**", corsConfig);

        return source;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        ProviderManager authenticationManager = new ProviderManager(Arrays.asList(daoAuthenticationProvider()));
        //不擦除认证密码，擦除会导致TokenBasedRememberMeServices因为找不到Credentials再调用UserDetailsService而抛出UsernameNotFoundException
        authenticationManager.setEraseCredentialsAfterAuthentication(false);
        return authenticationManager;
    }

    /**
     * 配置provider(DaoAuthenticationProvider) 用户名密码的一套
     * @return
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        return daoAuthenticationProvider;
    }


    /**
     * 配置地址栏不能识别 // 的情况
     * @return
     */
    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        //此处可添加别的规则,目前只设置 允许双 //
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return firewall;
    }


}