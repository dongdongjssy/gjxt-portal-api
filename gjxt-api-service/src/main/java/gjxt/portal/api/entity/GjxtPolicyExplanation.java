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
import java.io.Serializable;

/**
 * <p>
 * 政策解读
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("gjxt_policy_explanation")
@ToString
public class GjxtPolicyExplanation extends GjxtBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * {"title":"政策","searchable":"0"}
     */
    @TableField("policy")
    @NotBlank(message = "政策标题不能为空")
    private String policy;

    /**
     * {"title":"解读","searchable":"0"}
     */
    @TableField("explanation")
    @NotBlank(message = "政策解读不能为空")
    private String explanation;

    /**
     * {"title": "类别", "type": "dict", "value": "education_level", "data":{"0":"中职", "1":"本专科", "2":"研究生"}, "searchable": "0"}
     */
    @TableField("category")
    private Integer category;

    /**
     * {"title": "状态", "type": "dict", "value": "status", "data":{"0":"正常", "1":"删除"}, "searchable": "1"}
     */
    @TableField("status")
    private Integer status;
}
