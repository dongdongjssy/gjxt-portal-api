package gjxt.portal.apigateway.sercurity.dto;

import lombok.Data;

import java.util.Map;

@Data
public class VerifyImgeResponse {
    private Boolean fail;
    private Integer code;
    private String message;
    private Map<String, Object> resultMap;
}
