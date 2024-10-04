package gjxt.portal.api.handler;

import gjxt.portal.api.common.base.R;
import gjxt.portal.api.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GjxtExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public R argumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("参数验证异常：", e);

        String errorMsg = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(","));

        return R.error(HttpStatus.BAD_REQUEST.value(), errorMsg);
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public R businessExceptionHandler(BusinessException e) {
        log.error("业务异常：", e);
        return R.error(
                e.getRestResultCode().getCode(),
                Objects.isNull(e.getMessage()) ? e.getRestResultCode().getMessage() : e.getMessage()
        );
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R exceptionHandler(Exception e) {
        log.error("未知异常: ", e);
        return R.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "请求出错，请检查请求参数或者联系管理员。");
    }
}
