package gjxt.portal.apigateway.sercurity.config;

import gjxt.portal.apigateway.sercurity.domain.auth.UserDetail;
import gjxt.portal.apigateway.sercurity.utils.JwtConfig;
import gjxt.portal.apigateway.sercurity.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 2. 从AuthenticationToken读取Token并做用户数据解析
 *
 * @author zhe.xiao
 * @date 2021-04-14 23:35
 * @description
 */
@Slf4j
@Component
public class ScAuthenticationManager implements ReactiveAuthenticationManager {
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private JwtConfig jwtCfg;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String auth_token = (String) authentication.getPrincipal();
        String bearer = jwtCfg.getPrefix();
        //filter authorization token
        if(StringUtils.isNotBlank(auth_token) && auth_token.startsWith(bearer) ) {
            auth_token = auth_token.replace(bearer, "");
        }
        //校验token
        UserDetail userDetail = jwtUtils.getUserFromToken(auth_token);
        log.info("ScAuthenticationManager scUser = {}", userDetail);
        UsernamePasswordAuthenticationToken authenticate = new UsernamePasswordAuthenticationToken(userDetail, null, null);
        return Mono.just(authentication).map(auth -> authenticate);
    }
}
