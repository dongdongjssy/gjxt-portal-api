package gjxt.portal.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 通知公告
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("gjxt_announcement")
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GjxtAnnouncement extends GjxtBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * {"title": "标题", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}
     */
    @TableField("title")
    @NotBlank(message = "通知公告标题不能为空")
    private String title;

    /**
     * {"title": "内容","searchable": "0","textSearchOpt":"1","richEditor":"2"}
     */
    @TableField("content")
    @NotBlank(message = "通知公告内容不能为空")
    private String content;

    /**
     * {"title": "状态", "type": "dict", "value": "status", "data":{"0":"正常", "1":"删除"}, "searchable": "1"}
     */
    @TableField("status")
    private Integer status;

    private List<GjxtMultimedia> medias;
}
