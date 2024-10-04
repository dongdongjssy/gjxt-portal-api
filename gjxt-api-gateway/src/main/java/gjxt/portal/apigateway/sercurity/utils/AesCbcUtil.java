package gjxt.portal.apigateway.sercurity.utils;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Security;
/**
 * 描述: 解密
 */
public class AesCbcUtil {
	
	static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * AES解密
     *
     * @param data     //被加密的数据
     * @param key      //加密秘钥
     * @param iv       //偏移量
     * @param encoding //解密后的结果需要进行的编码
     */
    public static String decrypt(String data, String key, String iv, String encoding) {

        // org.apache.commons.codec.binary.Base64
        byte[] dataByte = Base64.decodeBase64(data);
        byte[] keyByte = Base64.decodeBase64(key);
        byte[] ivByte = Base64.decodeBase64(iv);

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));

            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                return new String(resultByte, encoding);
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
