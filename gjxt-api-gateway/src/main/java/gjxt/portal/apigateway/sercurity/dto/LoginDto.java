package gjxt.portal.apigateway.sercurity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author hxy
 * @date 2023/4/24
 */
@Data
public class LoginDto {
    @NotNull(message = "密码不能为空")
    private String password;

    @NotNull(message = "手机号不能为空")
    private String phone;

    private String smsCode;

//    @NotNull(message = "uuid不能为空")
    private String uuid;

    private String idNumber;
}
