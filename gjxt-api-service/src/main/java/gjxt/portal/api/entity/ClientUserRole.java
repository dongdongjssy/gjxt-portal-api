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

/**
 * <p>
 * 客户端用户角色关联
 * </p>
 *
 * @author hxy
 * @since 2023-02-23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("client_user_role")
public class ClientUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * {"title": "用户", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"client_user", "fkColumn":"id", "fkDisplayColumn":"nickname", "fkInputMethod":"select"}
     */
    @TableField("user_id")
    private Long userId;

    /**
     * {"title": "角色", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"client_role", "fkColumn":"id", "fkDisplayColumn":"name", "fkInputMethod":"select"}
     */
    @TableField("role_id")
    private Integer roleId;



@Override
public String toString() {
        return "ClientUserRole{" +
            "id=" + id +
            ", userId=" + userId +
            ", roleId=" + roleId +
        "}";
        }
}
