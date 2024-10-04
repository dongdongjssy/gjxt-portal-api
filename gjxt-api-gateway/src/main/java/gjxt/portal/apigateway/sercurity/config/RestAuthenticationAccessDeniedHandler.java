package gjxt.portal.apigateway.sercurity.config;

import com.alibaba.fastjson.JSONObject;
import gjxt.portal.apigateway.sercurity.domain.ResultCode;
import gjxt.portal.apigateway.sercurity.domain.ResultJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * @author: goose
 * createAt: 2018/9/21
 */
@Component("RestAuthenticationAccessDeniedHandler")
public class RestAuthenticationAccessDeniedHandler implements ServerAccessDeniedHandler {
	
    private static final Logger log = LoggerFactory.getLogger(RestAuthenticationAccessDeniedHandler.class);

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException e) {
        //登陆状态下，权限不足执行该方法
        log.info("权限不足：" + e.getMessage());

        return Mono.defer(() -> Mono.just(exchange.getResponse()))
                .flatMap(response -> {
                    response.setStatusCode(HttpStatus.OK);
                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                    DataBufferFactory dataBufferFactory = response.bufferFactory();
                    String result = ResultJson.failure(ResultCode.FORBIDDEN, e.getMessage()).toString();
                    DataBuffer buffer = dataBufferFactory.wrap(result.getBytes(
                            Charset.defaultCharset()));
                    return response.writeWith(Mono.just(buffer));
                });


    }
}
