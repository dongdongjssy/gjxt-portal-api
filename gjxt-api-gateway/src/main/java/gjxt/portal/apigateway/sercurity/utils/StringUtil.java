package gjxt.portal.apigateway.sercurity.utils;

import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chendy on 2021/2/20 0020.
 */
public class StringUtil {

    /** 空字符串 */
    private static final String NULLSTR = "";


    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isEmpty(Object o) {
        return o == null;
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }


    public static String replaceAll(String source, String regex, String target) {
        if (StringUtils.hasText(source)) {
            return source.replaceAll(regex, target);
        }
        return "";
    }
    /**
     * 生成随机字符串
     */
    public static String uuid(){
        return  UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 生成随机字符串
     */
    public static String BigUuid(){
        return  uuid().toUpperCase();
    }

    /**
     * 截取字符串
     *
     * @param str 字符串
     * @param start 开始
     * @param end 结束
     * @return 结果
     */
    public static String substring(final String str, int start, int end)
    {
        if (str == null)
        {
            return NULLSTR;
        }

        if (end < 0)
        {
            end = str.length() + end;
        }
        if (start < 0)
        {
            start = str.length() + start;
        }

        if (end > str.length())
        {
            end = str.length();
        }

        if (start > end)
        {
            return NULLSTR;
        }

        if (start < 0)
        {
            start = 0;
        }
        if (end < 0)
        {
            end = 0;
        }

        return str.substring(start, end);
    }

    // 把一个字符串的第一个字母大写、效率是最高的
    public static String getMethodName(String fieldName) throws Exception{
        byte[] items = fieldName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    public static String parseStr(String source, String regex) {
        if (StringUtil.isEmpty(source)) {
            return source;
        }
        String[] temps = source.split("\n");
        StringBuilder buffer = new StringBuilder();
        for (String item : temps) {
            buffer.append(regex).append(item.replaceAll("\u00a0", " ").trim());
            buffer.append("\n");
        }
        return buffer.toString();
    }

    public static String replaceAll(String source,String rule, String regex, String target) {
        if (!StringUtils.hasText(source)) {
            return null;
        }
        Pattern pattern = Pattern.compile(rule);
        Matcher matcher = pattern.matcher(source);
        if (matcher.matches()) {
            return source.replaceAll(regex, target);
        }
        return null;
    }
}
