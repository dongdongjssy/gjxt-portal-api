package gjxt.portal.apigateway.sercurity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VerifyImgeVo {
    @NotNull(message = "请使用图片验证码")
    private Integer verifykey;
    @NotNull(message = "用户信息不能为空")
    private LoginDto loginDto;
}
