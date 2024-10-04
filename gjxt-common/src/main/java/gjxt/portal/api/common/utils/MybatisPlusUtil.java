package gjxt.portal.api.common.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hxy
 * @date 2022/7/1
 */
public class MybatisPlusUtil {
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    public static<T> void notNullField(T T, QueryWrapper<T> wrapper){
        for (Field field : T.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                //序列化 字段不需要查询
                if("serialVersionUID".equals(field.getName())){
                    continue;
                }
                //属性为空，不用查询
                if(StringUtils.isEmpty(field.get(T))){
                    continue;
                }
                //主键 注解TableId
                TableId tableId = field.getAnnotation(TableId.class);
                if (tableId != null){
                    //主键
                    wrapper.eq(tableId.value(),field.get(T));
                    continue;
                }
                //数据库中字段名和实体类属性不一致 注解TableField
                TableField tableField = field.getAnnotation(TableField.class);
                if(tableField != null){
                    if(tableField.exist()&&StringUtils.hasText(tableField.value())){
                        wrapper.eq(tableField.value(),field.get(T));
                    }// @TableField(exist = false) 不是表中内容 不形成查询条件
                    continue;
                }
                if(tableField==null) continue;
                //数据库中字段名和实体类属性一致
                wrapper.eq(humpToLine(field.getName()),field.get(T));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
