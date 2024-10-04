package gjxt.portal.backgateway.sercurity.config;

import gjxt.portal.backgateway.sercurity.utils.JwtConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * 1. 把header拿到的token放入AuthenticationToken
 *
 * @author zhe.xiao
 * @date 2021-04-14 23:32
 * @description
 */
@Slf4j
@Component
public class ScSecurityContextRepository implements ServerSecurityContextRepository {
    @Autowired
    ScAuthenticationManager scAuthenticationManager;

    @Resource
    private JwtConfig jwtCfg;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        String authorization = request.getHeaders().getFirst(jwtCfg.getHeader());
        log.info("ScSecurityContextRepository authorization = {}", authorization);

        return scAuthenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authorization, null))
                .map(SecurityContextImpl::new);
    }
}
