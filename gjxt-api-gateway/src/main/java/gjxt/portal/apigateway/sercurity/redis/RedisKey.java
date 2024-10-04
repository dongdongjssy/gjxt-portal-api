package gjxt.portal.apigateway.sercurity.redis;

import gjxt.portal.apigateway.sercurity.utils.StringAlgorithmUtils;

import javax.servlet.http.HttpSession;

/**
 * @author hxy
 * @date 2023/5/11
 */
public class RedisKey {

    public static final String VERIFICATIONCODE_LOGIN = "portal:verificationCode:login:";
    private static final String SPLIT = ":";

    public static final String LONG_ACCOUNT_LOCK = "portal:account:lock:";

    public static final String LONG_INPUT_COUNT = "portal:loginInput:count:";
    public static final String HAD_OVERRIDE_DATA = ":portal:override_data";

    public static final String VERIFICATIONCODE_RETRIEVE = "portal:verificationCode:retrieve:";
    public static final String VERIFICATIONCODE_PIC = "portal:verificationCode:pic:";
    public static final String VERIFICATIONCODE_DAY_TOTAL_NUM = "portal:dayTotalNum:";

    public static String generateKey(String redisKey) {
        return generateKey(null, redisKey);
    }

    public static String generateKey(String uuid, String redisKey) {
        StringBuilder stringBuilder = new StringBuilder();
        if (uuid != null) {
            stringBuilder.append(uuid).append(SPLIT);
        }
        stringBuilder.append(redisKey);
        return StringAlgorithmUtils.MD5Encode(stringBuilder.toString());
    }

    public static String buildKey(String ...keys){
        StringBuilder sb = new StringBuilder("");
        for (String key : keys) {
            sb.append(key);
        }
        return sb.toString();
    }
}
