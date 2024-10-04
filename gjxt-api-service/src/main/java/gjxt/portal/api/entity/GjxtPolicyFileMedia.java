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

import java.io.Serializable;

/**
 * <p>
 * 政策文件关联媒体
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("gjxt_policy_file_media")
@ToString
public class GjxtPolicyFileMedia implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * {"title": "政策文件", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"gjxt_policy_file", "fkColumn":"id", "fkDisplayColumn":"title", "fkInputMethod":"select"}
     */
    @TableField("policy_file_id")
    private Long policyFileId;

    /**
     * {"title": "媒体", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"gjxt_multimedia", "fkColumn":"id", "fkDisplayColumn":"media_title", "fkInputMethod":"select"}
     */
    @TableField("media_id")
    private Long mediaId;

    private GjxtMultimedia media;
}