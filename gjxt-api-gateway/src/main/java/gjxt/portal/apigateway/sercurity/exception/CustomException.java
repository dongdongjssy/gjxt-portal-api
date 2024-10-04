package gjxt.portal.apigateway.sercurity.exception;

import gjxt.portal.apigateway.sercurity.domain.ResultJson;
import lombok.Getter;

/**
 * @author goose
 * createAt: 2019/4/1.
 */
@Getter
public class CustomException extends RuntimeException{
    private ResultJson resultJson;

    private String message;

    private int code;

    public CustomException(ResultJson resultJson) {
        this.resultJson = resultJson;
        this.code=resultJson.getCode();
        this.message=resultJson.getMsg();
    }
}
