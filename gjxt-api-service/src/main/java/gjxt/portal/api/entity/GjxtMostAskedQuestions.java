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
 * 常见问题
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("gjxt_most_asked_questions")
@ToString
public class GjxtMostAskedQuestions extends GjxtBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * {"title":"问题","searchable":"0"}
     */
    @TableField("question")
    @NotBlank(message = "问题不能为空")
    private String question;

    /**
     * {"title":"解答","searchable":"0"}
     */
    @TableField("answer")
    @NotBlank(message = "回答不能为空")
    private String answer;

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
