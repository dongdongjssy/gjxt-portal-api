package gjxt.portal.api.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

/**
 * @author hxy
 * @date 2022/9/30
 */
public class DateUtils {

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static Integer HOUR=3600000;

    public static final String dateTimeNow()
    {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }
    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public static final LocalDateTime parseDate(String format) throws ParseException {
        Date date=new SimpleDateFormat(YYYYMMDDHHMMSS).parse(format);
        return toLocal(date);
    }

    public static LocalDateTime toLocal(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static String getToday(){
        LocalDateTime now = LocalDateTime.now();
        return now.getYear()+"-"+(now.getMonthValue()>9?now.getMonthValue():"0"+now.getMonthValue())+"-"+(now.getDayOfMonth()>9?now.getDayOfMonth():"0"+now.getDayOfMonth());
    }

    public static String getDateStr(LocalDateTime dateTime){
        return dateTime.getYear()+"-"+(dateTime.getMonthValue()>9?dateTime.getMonthValue():"0"+dateTime.getMonthValue())+"-"+(dateTime.getDayOfMonth()>9?dateTime.getDayOfMonth():"0"+dateTime.getDayOfMonth())+" 00:00:00";
    }

    public static String getFormat(Date date){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static Long getMilli(LocalDateTime time){
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }


}
