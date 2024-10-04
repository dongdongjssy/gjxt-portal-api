package gjxt.portal.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 系统验证码
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("sys_captcha")
public class SysCaptcha {
    @TableId(type = IdType.INPUT)
    private String uuid;
    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    @TableField("status")
    private Integer status;

    @TableField("create_id")
    private Long createId;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField("modify_id")
    private Long modifyId;

    @TableField("modify_time")
    private LocalDateTime modifyTime;

}
