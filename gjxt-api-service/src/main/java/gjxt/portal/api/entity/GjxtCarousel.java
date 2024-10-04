package gjxt.portal.api.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
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
import java.time.LocalDateTime;

/**
 * <p>
 * 轮播图
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("gjxt_carousel")
@ToString
public class GjxtCarousel extends GjxtBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * {"title":"轮播图序号"}
     */
    @TableField("carousel_order")
    private Integer carouselOrder;

    /**
     * {"title":"轮播图路径"}
     */
    @TableField("source")
    @NotBlank(message = "轮播图路径不能为空")
    private String source;

    /**
     * {"title":"轮播图说明文字"}
     */
    @TableField("description")
    private String description;

    /**
     * {"title": "状态", "type": "dict", "value": "status", "data":{"0":"正常", "1":"删除"}, "searchable": "1"}
     */
    @TableField("status")
    private Integer status;
}
