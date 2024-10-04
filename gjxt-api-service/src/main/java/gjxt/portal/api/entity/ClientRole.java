package gjxt.portal.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * <p>
 * 客户端角色
 * </p>
 *
 * @author hxy
 * @since 2023-02-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("client_role")
public class ClientRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * {"title": "角色", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}
     */
    @TableField("name")
    private String name;

    /**
     * {"title": "描述", "searchable": "0", "textSearchOpt":"1", "richEditor":"0"}
     */
    @TableField("description")
    private String description;



@Override
public String toString() {
        return "ClientRole{" +
            "id=" + id +
            ", name=" + name +
            ", description=" + description +
        "}";
        }
}
