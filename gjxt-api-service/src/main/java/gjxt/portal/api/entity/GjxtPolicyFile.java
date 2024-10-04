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
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 政策文件
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("gjxt_policy_file")
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GjxtPolicyFile extends GjxtBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * {"title": "标题", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}
     */
    @TableField("title")
    @NotBlank(message = "政策文件标题不能为空")
    private String title;

    /**
     * {"title": "内容","searchable": "0","textSearchOpt":"1","richEditor":"2"}
     */
    @TableField("content")
    @NotBlank(message = "政策文件内容不能为空")
    private String content;

    /**
     * {"title": "教育阶段", "type": "dict", "value": "education_level", "data":{"0":"中职", "1":"本专科", "2":"研究生"}, "searchable": "0"}
     */
    @TableField("education_level")
    @NotNull(message = "政策文件的教育阶段不能为空")
    private Integer educationLevel;

    /**
     * {"title": "状态", "type": "dict", "value": "status", "data":{"0":"正常", "1":"删除"}, "searchable": "1"}
     */
    @TableField("status")
    private Integer status;

    private List<GjxtMultimedia> medias;
}
