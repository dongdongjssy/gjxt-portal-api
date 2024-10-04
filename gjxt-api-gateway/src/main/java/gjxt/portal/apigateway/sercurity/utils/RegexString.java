package gjxt.portal.apigateway.sercurity.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexString {
    /**
     * 中国大陆身份证格式校验
     */
    public static final String ID_CARD = "([1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx])|([1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3})";
    /**
     * 香港
     */
    public static final String HK_ID_CARD = "[A-Z]{1,2}[0-9]{6}[(|（]?[0-9A][)|）]?";
    /**
     * 澳门
     */
    public static final String MACAO_ID_CARD = "[1|5|7][0-9]{6}[(|（]?[0-9A-Z][)|）]?";
    /**
     * 台湾
     */
    public static final String TAIWAN_ID_CARD = "[a-zA-Z0-9]{8,9}";

    public static final String ALL_ID = "(([1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx])|([1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}))|([A-Z]{1,2}[0-9]{6}[(|（]?[0-9A][)|）]?)|([1|5|7][0-9]{6}[(|（]?[0-9A-Z][)|）]?)|([a-zA-Z][0-9]{9})";
    /**
     * 日期（2021-12-12）
     */
    public static final String DATE_REGEX = "^((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$";

    public static String REGEX_CHINESE = "[\u4e00-\u9fa5]";

    public static String REGEX_CHINESE2 = "[\u4e00-\u9fa5a-zA-Z0-9]";

    public static String REGEX_NAME = "^([\u4e00-\u9fa5]{2,30}|[a-zA-Z.\\s]{2,40})$";

    public static String REGEX_EN_NO = "[a-zA-Z0-9]";

    public static String REGEX_NUMBER = "^[0-9]*$";

    public static String REGEX_SPE = "[`~!@#$^&*()=|{}':;',\\[\\].<>?~！@#￥……&*（）——|{}【】‘；：”“'。，、？\'\"\\r\\n ]";

    public static String REGEX_SPECIAL_NO = "[^\\u4e00-\\u9fa5A-Za-z0-9]";

    public static String REGEX_SPECIAL_NO_2 = "[^\\u4e00-\\u9fa5A-Za-z0-9\\/]";

    public static String REGEX_SPECIAL = "[\\u4e00-\\u9fa5A-Za-z0-9]";

    public static String REGEX_SPECIAL_NORMAL = "[A-Za-z0-9\\u4e00-\\u9fa5 《》\"\"''<>‘’“”（）(){}\\[\\]【】]";

    public static final String FULL_DATE_REGEX = "^([1-2]\\d{3})[年.\\-/ ]*?(1[0-2]|[0]?[1-9])[月.\\-/ ]*?([1-2][0-9]|3[0-1]|[0]?[1-9])[日.\\-/ ]*?$";

    public static final String FULL_DATE_REGEX_EXTRA = "([1-2]\\d{3})[年.\\-/ ]*?(1[0-2]|[0]?[1-9])[月.\\-/ ]*?([1-2][0-9]|3[0-1]|[0]?[1-9])[日.\\-/ ]*?";

    public static final String Y_M_REGEX = "^([1-2]\\d{3})[年.\\-/ ]*?(1[0-2]|[0]?[1-9])[月.\\-/ ]*?$";

    public static final String Y_M_REGEX_EXTRA = "([1-2]\\d{3})[年.\\-/ ]*?(1[0-2]|[0]?[1-9])[月.\\-/ ]*?";

    public static final String BANK_CARD_NUMBER = "([0-9]{16}|[0-9]{17}|[0-9]{19})";

    /**
     * 导入excel的日期格式转化
     */
    public static String daysToDate(String num) {
        Pattern pattern = Pattern.compile(REGEX_NUMBER);
        Matcher matcher = pattern.matcher(num);
        if (matcher.matches()) {
            LocalDate localDate = LocalDate.of(1990, 1, 1);
            localDate = localDate.plusDays(Integer.parseInt(num));
            return localDate.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
        }
        return num;
    }

}
