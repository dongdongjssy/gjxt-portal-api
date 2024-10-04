package gjxt.portal.apigateway.sercurity.utils;

import java.util.Calendar;

public class DateUtils {

    /**
     * 计算第二天凌晨与当前时间的时间差秒数
     * @param
     * @return java.lang.Long
     * @author shy
     * @date 2021/3/12 18:10
     */
    public static Long getNowToNextDaySeconds() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }
}
