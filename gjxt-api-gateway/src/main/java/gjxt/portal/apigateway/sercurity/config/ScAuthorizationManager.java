package gjxt.portal.apigateway.sercurity.config;

import gjxt.portal.apigateway.sercurity.domain.auth.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 3. 权限验证，是否放行
 *
 * @author zhe.xiao
 * @date 2021-04-15 11:12
 * @description
 */
@Slf4j
@Component
public class ScAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        return authentication.map(auth -> {
            UserDetail scUser = (UserDetail) auth.getPrincipal();
            log.info("ScAuthorizationManager scUser = {}", scUser);

            if (Objects.isNull(scUser)) {
                return new AuthorizationDecision(false);
            }
            authorizationContext.getExchange().getRequest().mutate().header("userId",scUser.getUser().getId().toString());

            return new AuthorizationDecision(true);
        }).defaultIfEmpty(new AuthorizationDecision(false));
    }
}
