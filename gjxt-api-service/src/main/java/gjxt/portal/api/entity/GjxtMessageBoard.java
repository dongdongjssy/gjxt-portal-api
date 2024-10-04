package gjxt.portal.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 获奖心声
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("gjxt_message_board")
@ToString
public class GjxtMessageBoard extends GjxtBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * {"title":"内容","searchable":"0","richEditor":"2"}
     */
    @TableField("content")
    @NotBlank(message = "留言内容不能为空")
    private String content;

    /**
     * {"title":"位置","searchable":"1"}
     */
    @TableField("location")
    @NotBlank(message = "定位不能为空")
    private String location;

    /**
     * {"title":"发表方式","searchable":"1","type":"dict","value":"publish_mode","data":{"0":"实名","1":"匿名"}}
     */
    @TableField("publish_mode")
    @NotNull(message = "发布方式不能为空，请选择：0（实名）或者1（匿名）")
    private Integer publishMode;

    /**
     * {"title":"发布状态","searchable":"1","type":"dict","value":"publish_status","data":{"0":"待审核", "1":"通过", "2":"驳回", "3":"回收站"}}
     */
    @TableField("publish_status")
    private Integer publishStatus;

    private String realName;
    private String avatar;
}
