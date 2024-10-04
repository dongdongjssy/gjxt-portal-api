package gjxt.portal.apigateway.sercurity.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gjxt.portal.apigateway.sercurity.domain.ResultJson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.util.Iterator;
import java.util.List;

/**

 * <p>
 * 网关异常通用处理器，只作用在webflux 环境下 , 优先级低于 {@link ResponseStatusExceptionHandler} 执行
 */
@Slf4j
@Order(-1)
@RequiredArgsConstructor
@Configuration
public class GlobalExceptionConfiguration implements WebExceptionHandler {
    private final ObjectMapper objectMapper;
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();
        if (response.isCommitted()) {
            return Mono.error(ex);
        }        // header set
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        String message=ex.getMessage();
        if (ex instanceof ResponseStatusException) {
            ResponseStatusException  myGatewayException = (ResponseStatusException ) ex;
            response.setStatusCode(((ResponseStatusException) ex).getStatus());
        }

        if(ex instanceof WebExchangeBindException){
            message= getMessage((WebExchangeBindException) ex);
        }

        String finalMessage = message;
        return response
                .writeWith(Mono.fromSupplier(() -> {
                    DataBufferFactory bufferFactory = response.bufferFactory();
                    try {
                        return bufferFactory.wrap(objectMapper.writeValueAsBytes(ResultJson.failure(finalMessage)));
                    } catch (JsonProcessingException e) {
                        log.warn("Error writing response", ex);
                        return bufferFactory.wrap(new byte[0]);
                    }
                }));
    }

    protected String getMessage(WebExchangeBindException ex) {
        List<ObjectError> errors = ex.getAllErrors();
        if (!errors.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            Iterator<ObjectError> it = errors.iterator();
            while (it.hasNext()) {
                sb.append(it.next().getDefaultMessage());
                if (it.hasNext()) {
                    sb.append(" ");
                }
            }
            return sb.toString();
        }
        return ex.getMessage();
    }
}
