package gjxt.portal.backgateway.sercurity.exception;

import gjxt.portal.backgateway.sercurity.domain.ResultJson;
import lombok.Getter;

/**
 * @author goose
 * createAt: 2019/4/1.
 */
@Getter
public class CustomException extends RuntimeException{
    private ResultJson resultJson;

    public CustomException(ResultJson resultJson) {
        this.resultJson = resultJson;
    }
}
