package gjxt.portal.apigateway.sercurity.exception;

import gjxt.portal.apigateway.sercurity.domain.ResultJson;
import lombok.Getter;

/**
 * @author goose
 * createAt: 2019/8/7.
 */
@Getter
public class TokenExpiredException extends RuntimeException{
    private ResultJson resultJson;

    public TokenExpiredException(ResultJson resultJson) {
        this.resultJson = resultJson;
    }
}