package gjxt.portal.backgateway.sercurity.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Data
@Component
public class JwtConfig {
	
    @Value("${jwt.header}")
    private String header; 
    
    @Value("${jwt.prefix}")
    private String prefix;
    
    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.accessTokenHeader}")
    private String accessTokenHeader;
    
    @Value("${jwt.refreshTokenHeader}")
    private String refreshTokenHeader;
    
    @Value("${jwt.accessTokenExpiration}")
    private Long accessTokenExpiration;
    
    @Value("${jwt.refreshTokenExpiration}")
    private Long refreshTokenExpiration;
    
    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;  
    public static final String ROLE_REFRESH_TOKEN = "ROLE_REFRESH_TOKEN";
    public static final String CLAIM_KEY_USER_ID = "user_id";
    public static final String CLAIM_KEY_AUTHORITIES = "scope";
    public static final String ISSUER = "securityserver";
    public static final String CLAIM_KEY_ROLE = "role";
    public static final String CLAIM_KEY_STATUS = "status";
}
