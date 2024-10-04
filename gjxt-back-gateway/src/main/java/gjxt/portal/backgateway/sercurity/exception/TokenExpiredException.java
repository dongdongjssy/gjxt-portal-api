package gjxt.portal.backgateway.sercurity.exception;

import gjxt.portal.backgateway.sercurity.domain.ResultJson;
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