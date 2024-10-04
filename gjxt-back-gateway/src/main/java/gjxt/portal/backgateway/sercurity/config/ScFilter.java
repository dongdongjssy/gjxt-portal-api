package gjxt.portal.backgateway.sercurity.config;

import gjxt.portal.backgateway.sercurity.domain.auth.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * 4. 请求通过后的额外操作处理
 *
 * @author zhe.xiao
 * @date 2021-04-13 15:01
 * @description
 */
@Slf4j
public class ScFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("UserFilter doing.... path={}", exchange.getRequest().getPath());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!Objects.isNull(authentication)) {
            UserDetail userDetail = (UserDetail) authentication.getPrincipal();
            exchange.getRequest().mutate().header("userId",userDetail.getUser().getUserId().toString());
            log.info("UserFilter doing principal={}", userDetail);
        }

        return chain.filter(exchange);
    }
}
