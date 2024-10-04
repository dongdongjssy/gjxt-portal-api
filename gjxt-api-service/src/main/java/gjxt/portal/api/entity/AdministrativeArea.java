package gjxt.portal.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * 行政区域表
 * </p>
 *
 * @author hxy
 * @since 2023-05-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("administrative_area")
public class AdministrativeArea implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 行政区域id
     */
    @TableId("id")
    private String id;

    /**
     * 行政区域类型
     */
    @TableField("types")
    private String types;

    /**
     * 行政区域名称
     */
    @TableField("name")
    private String name;

    /**
     * 上级行政区域id
     */
    @TableField("pre_id")
    private String preId;

    /**
     * 上级行政区域名称
     */
    @TableField("pre_name")
    private String preName;

    /**
     * 学校机构举办者码
     */
    @TableField("school_code")
    private String schoolCode;

    /**
     * 全路径
     */
    @TableField("full_path")
    private String fullPath;

    /**
     * 祖级列表
     */
    @TableField("ancestral_list")
    private String ancestralList;

    /**
     * 全名称
     */
    @TableField("full_name")
    private String fullName;

    /**
     * 管辖全路径(逗号隔开)
     */
    @TableField("manage_full_path")
    private String manageFullPath;

    /**
     * 是否最后一个节点
     */
    @TableField("last_level")
    private Integer lastLevel;

    /**
     * 等级(1,2,3,4)
     */
    @TableField("level")
    private Integer level;

    /**
     * 0 普通 1单例市 2 直辖市
     */
    @TableField("prop")
    private Integer prop;



@Override
public String toString() {
        return "AdministrativeArea{" +
            "id=" + id +
            ", types=" + types +
            ", name=" + name +
            ", preId=" + preId +
            ", preName=" + preName +
            ", schoolCode=" + schoolCode +
            ", fullPath=" + fullPath +
            ", ancestralList=" + ancestralList +
            ", fullName=" + fullName +
            ", manageFullPath=" + manageFullPath +
            ", lastLevel=" + lastLevel +
            ", level=" + level +
            ", prop=" + prop +
        "}";
        }
}
