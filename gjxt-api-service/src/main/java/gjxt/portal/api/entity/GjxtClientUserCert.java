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
 * 客户端用户证书关联
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("gjxt_client_user_cert")
@ToString
public class GjxtClientUserCert implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * {"title": "用户", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"client_user", "fkColumn":"id", "fkDisplayColumn":"username", "fkInputMethod":"select"}
     */
    @TableField("user_id")
    private Long userId;

    /**
     * {"title": "证书", "searchable": "0", "fkCreateAssoc": "1", "fkTable":"certificate", "fkColumn":"id", "fkDisplayColumn":"name", "fkInputMethod":"select"}
     */
    @TableField("cert_id")
    private Long certId;

    private Integer certEducationLevel;

    private ClientUser user;
    private GjxtCertificate cert;

}
