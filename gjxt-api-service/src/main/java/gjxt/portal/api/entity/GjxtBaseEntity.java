package gjxt.portal.api.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import gjxt.portal.api.validate.Add;
import gjxt.portal.api.validate.Update;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@ToString
public class GjxtBaseEntity {

    /**
     * {"title": "创建者", "searchable":"1", "systemField":"1"}
     */
    @TableField("create_by")
    @NotNull(groups = Add.class, message = "发布者用户ID不能为空")
    private Long createBy;

    /**
     * {"title": "创建时间", "searchable": "1", "type": "date", "systemField":"1"}
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @NotNull(groups = Add.class, message = "发布时间不能为空")
    private LocalDateTime createTime;

    /**
     * {"title": "更新者", "searchable":"1", "systemField":"1"}
     */
    @TableField("modify_by")
    @NotNull(groups = Update.class, message = "更新者用户ID不能为空")
    private Long modifyBy;

    /**
     * {"title": "更新时间", "searchable": "1", "type": "date", "systemField":"1"}
     */
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @NotNull(groups = Update.class, message = "更新时间不能为空")
    private LocalDateTime modifyTime;
}
