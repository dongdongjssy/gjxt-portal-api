package gjxt.portal.api.common.exception;


import gjxt.portal.api.common.base.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qiull
 * @date 2021/12/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private ResultCode restResultCode;

    private String message;

    public BusinessException(String message) {
        this.message = message;
    }

    public BusinessException(ResultCode restResultCode) {
        this.restResultCode = restResultCode;
    }
}
