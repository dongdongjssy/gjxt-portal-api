package gjxt.portal.api.entity;

import com.alibaba.fastjson.annotation.JSONField;
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
import java.time.LocalDateTime;

/**
 * <p>
 * 客户端用户
 * </p>
 *
 * @author hxy
 * @since 2023-05-04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("client_user")
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * {"title": "用户名", "searchable": "1", "textSearchOpt":"1", "richEditor":"0", "checkUnique":"1"}
     */
    @TableField("username")
    private String username;

    /**
     * {"title": "手机号", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}
     */
    @TableField("phone")
    private String phone;

    /**
     * {"title": "状态", "type": "dict", "value": "status", "data":{"0":"正常", "1":"删除"}, "searchable": "1"}
     */
    @TableField("status")
    private Integer status;

    /**
     * {"title": "密码", "searchable": "0", "textSearchOpt":"0", "richEditor":"0", "systemField":"1"}
     */
    @TableField("password")
    private String password;

    /**
     * {"title": "密码重置时间", "searchable": "0", "textSearchOpt":"0", "type": "date", "systemField":"1"}
     */
    @TableField("password_reset_date")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime passwordResetDate;

    /**
     * {"title": "用户类型", "type": "dict", "value": "client_user_user_type", "data":{"0":"管理员", "1":"普通用户"}, "searchable": "0"}
     */
    @TableField("user_type")
    private Integer userType;

    /**
     * {"title": "用户头像", "uploadPic":"1"}
     */
    @TableField("avatar")
    private String avatar;

    /**
     * {"title": "身份证号", "searchable": "1", "textSearchOpt": "0", "richEditor": "0"}
     */
    @TableField("id_number")
    private String idNumber;

    /**
     * {"title": "真实姓名", "searchable": "1", "textSearchOpt": "0", "richEditor": "0"}
     */
    @TableField("real_name")
    private String realName;

    /**
     * {"title": "教育阶段", "type": "dict", "value": "education_level", "data":{"0":"中职", "1":"本专科", "2":"研究生"}, "searchable": "0"}
     */
    @TableField("education_level")
    private Integer educationLevel;

    /**
     * {"title":"个人简介","searchable":"0","richEditor":"2"}
     */
    @TableField("resume")
    private String resume;

    /**
     * {"title": "学校名称", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}
     */
    @TableField("school_name")
    private String schoolName;

    /**
     * {"title": "院系", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}
     */
    @TableField("faculty_name")
    private String facultyName;

    /**
     * {"title": "专业", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}
     */
    @TableField("profession")
    private String profession;

    /**
     * {"title": "学号", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}
     */
    @TableField("student_id")
    private String studentId;

    /**
     * {"title": "性别", "type": "dict", "value": "gender", "data":{"0":"-", "1":"男", "2":"女"}, "searchable": "0"}
     */
    @TableField("gender")
    private Integer gender;

    /**
     * {"title": "民族", "searchable": "1", "textSearchOpt":"1", "richEditor":"0"}
     */
    @TableField("nationality")
    private String nationality;

    /**
     * {"title": "入学时间", "searchable": "1", "type": "date"}
     */
    @TableField("admission_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime admissionTime;
}
