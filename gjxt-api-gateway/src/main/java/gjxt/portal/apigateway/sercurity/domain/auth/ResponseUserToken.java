package gjxt.portal.apigateway.sercurity.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author goose
 * createAt: 2019/4/1
 */
@Data
@AllArgsConstructor
public class ResponseUserToken {
    private String token;
    private String refreshToken;
    private Date issueDate;
    private Long expiration;
    private UserDetail userDetail;
}
