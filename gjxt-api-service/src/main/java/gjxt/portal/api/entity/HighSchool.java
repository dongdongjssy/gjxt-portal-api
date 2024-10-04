package gjxt.portal.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * <p>
 * 用户学校表
 * </p>
 *
 * @author hxy
 * @since 2023-05-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("high_school")
public class HighSchool implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学校机构id
     */
    @TableId("id")
    private String id;

    /**
     * 学校机构标识码
     */
    @TableField("identification_code")
    private String identificationCode;

    /**
     * 学校机构名称
     */
    @TableField("school_name")
    private String schoolName;

    /**
     * 学校机构举办者码
     */
    @TableField("school_code")
    private String schoolCode;

    /**
     * 学校机构办学类型码
     */
    @TableField("school_code_type")
    private String schoolCodeType;

    /**
     * 学校主管部门码
     */
    @TableField("manage_code")
    private String manageCode;

    /**
     * 学校机构地址代码
     */
    @TableField("addr_code")
    private String addrCode;

    /**
     * 学校机构属地管理教育行政部门代码
     */
    @TableField("education_code")
    private String educationCode;

    /**
     * 系统更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private String updateTime;

    /**
     * 学校机构所属体系（教育部/人社部）
     */
    @TableField("school_system")
    private String schoolSystem;

    /**
     * 是否撤销（在用/撤销）
     */
    @TableField("revoke")
    private String revoke;

    /**
     * '00'-中职，'01'-高校
     */
    @TableField("school_type")
    private String schoolType;

    /**
     * 中职	1-教育部直属	2-省级直属	3-地市级直属 	4-县级管理学校 
     */
    @TableField("manage_type")
    private Integer manageType;

    /**
     * 学校机构创建部门代码
     */
    @TableField("create_type")
    private String createType;

    /**
     * 学校排序字段
     */
    @TableField("orders")
    private Integer orders;

    /**
     * 学校行政区域编码
     */
    @TableField("administrative_id")
    private String administrativeId;



@Override
public String toString() {
        return "HighSchool{" +
            "id=" + id +
            ", identificationCode=" + identificationCode +
            ", schoolName=" + schoolName +
            ", schoolCode=" + schoolCode +
            ", schoolCodeType=" + schoolCodeType +
            ", manageCode=" + manageCode +
            ", addrCode=" + addrCode +
            ", educationCode=" + educationCode +
            ", updateTime=" + updateTime +
            ", schoolSystem=" + schoolSystem +
            ", revoke=" + revoke +
            ", schoolType=" + schoolType +
            ", manageType=" + manageType +
            ", createType=" + createType +
            ", orders=" + orders +
            ", administrativeId=" + administrativeId +
        "}";
        }
}
