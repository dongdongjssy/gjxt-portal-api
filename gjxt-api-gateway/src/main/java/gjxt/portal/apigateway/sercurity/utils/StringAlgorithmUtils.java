package gjxt.portal.apigateway.sercurity.utils;

import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringAlgorithmUtils {

    private static final int ASCII_START = 97;
    private static final int ASCII_END = 122;
    private static final int DEFAULT_SIZE = 6;

    private static final char[] VALIDATE = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
    private static final int[] WEIGHT = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static final int STRICT_BALANCER = 11;

    private static final char[] cnArr = new char[]{'一','二','三','四','五','六','七','八','九'};
    private static final char[] chArr = new char[]{'十','百','千','万','亿'};

    public static String randomSalt(int size, boolean upper) {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        int i = size;
        while (i > 0) {
            builder.append((char) (ASCII_START + random.nextInt(ASCII_END - ASCII_START)));
            i--;
        }
        String result = builder.toString();
        if (upper) {
            result = result.toUpperCase();
        }
        return result;
    }

    public static String randomSalt(int size) {
        return randomSalt(size, false);
    }

    public static String randomSalt() {
        return randomSalt(DEFAULT_SIZE);
    }

    public static String MD5Encode(String source, String salt) {
        return MD5Encode(source + "ulrhfwnkttar");
    }

    public static String MD5Encode(String source) {
        return DigestUtils.md5DigestAsHex(source.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 匹配身份证
     */
    public static String popIdCard(String temp) {
        if (StringUtils.hasText(temp)) {
            Pattern pattern = Pattern.compile(RegexString.ID_CARD);
            Matcher matcher = pattern.matcher(temp);
            if (matcher.find()) {
                return matcher.group(0);
            }
            Pattern twPattern = Pattern.compile(RegexString.TAIWAN_ID_CARD);
            Matcher twMatcher = twPattern.matcher(temp);
            if (twMatcher.find()) {
                return twMatcher.group(0);
            }
            Pattern hkPattern = Pattern.compile(RegexString.HK_ID_CARD);
            Matcher hkMatcher = hkPattern.matcher(temp);
            if (hkMatcher.find()) {
                return hkMatcher.group(0);
            }
            Pattern amPattern = Pattern.compile(RegexString.MACAO_ID_CARD);
            Matcher amMatcher = amPattern.matcher(temp);
            if (amMatcher.find()) {
                return amMatcher.group(0);
            }
        }
        return null;
    }

    /**
     * 校验身份证号是否符合规范
     */
    public static boolean calculateChineseIdCard(String card) {
        if (card == null || card.length() != 18) {
            return false;
        }
        char lastOne = String.valueOf(card.charAt(17)).toUpperCase().charAt(0);
        int sum = 0;
        for (int i = 0; i < card.length() - 1; i++) {
            sum = sum + Integer.parseInt(String.valueOf(card.charAt(i))) * WEIGHT[i];
        }
        int mode = sum % STRICT_BALANCER;

        return VALIDATE[mode] == lastOne;
    }

    /**
     * 天数转日期
     */
    public static String fromDays(int days, String pattern) {
        Calendar calendar = new GregorianCalendar(1900, Calendar.JANUARY, -1);
        calendar.add(Calendar.DATE, days);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static int chineseNumber2Int(String chineseNumber){
        int result = 0;
        int temp = 1;//存放一个单位的数字如：十万
        int count = 0;//判断是否有chArr

        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean b = true;//判断是否是chArr
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
                if (c == cnArr[j]) {
                    if(0 != count){//添加下一个单位之前，先把上一个单位值添加到结果中
                        result += temp;
                        temp = 1;
                        count = 0;
                    }
                    // 下标+1，就是对应的值
                    temp = j + 1;
                    b = false;
                    break;
                }
            }
            if(b){//单位{'十','百','千','万','亿'}
                for (int j = 0; j < chArr.length; j++) {
                    if (c == chArr[j]) {
                        switch (j) {
                            case 0:
                                temp *= 10;
                                break;
                            case 1:
                                temp *= 100;
                                break;
                            case 2:
                                temp *= 1000;
                                break;
                            case 3:
                                temp *= 10000;
                                break;
                            case 4:
                                temp *= 100000000;
                                break;
                            default:
                                break;
                        }
                        count++;
                    }
                }
            }
            if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
                result += temp;
            }
        }
        return result;
    }
}
