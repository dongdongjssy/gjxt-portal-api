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
 * 工作动态关联媒体
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("gjxt_work_update_media")
@ToString
public class GjxtWorkUpdateMedia implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * {"title": "工作动态", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"gjxt_work_update", "fkColumn":"id", "fkDisplayColumn":"title", "fkInputMethod":"select"}
     */
    @TableField("work_update_id")
    private Long workUpdateId;

    /**
     * {"title": "媒体", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"gjxt_multimedia", "fkColumn":"id", "fkDisplayColumn":"media_title", "fkInputMethod":"select"}
     */
    @TableField("media_id")
    private Long mediaId;

    private GjxtMultimedia media;
}
