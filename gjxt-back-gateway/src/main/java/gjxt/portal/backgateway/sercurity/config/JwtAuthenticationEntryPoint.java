package gjxt.portal.backgateway.sercurity.config;


import gjxt.portal.backgateway.sercurity.domain.ResultCode;
import gjxt.portal.backgateway.sercurity.domain.ResultJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.nio.charset.Charset;


/**
 * @author: goose
 * createAt: 2019/4/1
 */
@Component
public class JwtAuthenticationEntryPoint implements ServerAuthenticationEntryPoint, Serializable {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        return Mono.defer(() -> Mono.just(exchange.getResponse())).flatMap(response -> {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            String result = ResultJson.failure(ResultCode.UNAUTHORIZED, e.getMessage()).toString();
            DataBuffer buffer = dataBufferFactory.wrap(result.getBytes(
                    Charset.defaultCharset()));
            return response.writeWith(Mono.just(buffer));
        });

    }
}
