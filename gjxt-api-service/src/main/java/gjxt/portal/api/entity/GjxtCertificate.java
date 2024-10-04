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

import java.io.Serializable;

/**
 * <p>
 * 获奖证书
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("gjxt_certificate")
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GjxtCertificate extends GjxtBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 证书id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * {"title": "教育阶段", "type": "dict", "value": "education_level", "data":{"0":"中职", "1":"本专科", "2":"研究生"}, "searchable": "0"}
     */
    @TableField("cert_education_level")
    private Integer certEducationLevel;

    /**
     * {"title": "证书编号", "searchable": "1", "textSearchOpt": "0", "richEditor": "0"}
     */
    @TableField("cert_number")
    private String certNumber;

    /**
     * {"title": "获奖学年", "searchable": "1", "textSearchOpt": "0", "richEditor": "0"}
     */
    @TableField("cert_issue_year")
    private String certIssueYear;

    /**
     * {"title": "证书图片", "uploadPic":"1"}
     */
    @TableField("image")
    private String image;

    private String idNumber;

    private String name;

    /**
     * {"title": "状态", "type": "dict", "value": "status", "data":{"0":"正常", "1":"删除"}, "searchable": "1"}
     */
    @TableField("status")
    private Integer status;
}
