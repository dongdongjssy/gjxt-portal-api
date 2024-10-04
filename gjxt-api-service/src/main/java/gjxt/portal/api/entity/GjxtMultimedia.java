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
 * 多媒体文件
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("gjxt_multimedia")
@ToString
public class GjxtMultimedia extends GjxtBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * {"title":"多媒体名称"}
     */
    @TableField("media_title")
    @NotBlank(message = "媒体名称不能为空")
    private String mediaTitle;

    /**
     * {"title":"多媒体路径"}
     */
    @TableField("media_src")
    @NotBlank(message = "媒体路径不能为空")
    private String mediaSrc;

    /**
     * {"title": "多媒体类型","type": "dict", "value": "media_type", "data":{"0":"文件", "1":"图片", "2":"视频"}, "searchable": "1"}
     */
    @TableField("media_type")
    private Integer mediaType;

    /**
     * {"title": "状态", "type": "dict", "value": "status", "data":{"0":"正常", "1":"删除"}, "searchable": "1"}
     */
    @TableField("status")
    private Integer status;
}
