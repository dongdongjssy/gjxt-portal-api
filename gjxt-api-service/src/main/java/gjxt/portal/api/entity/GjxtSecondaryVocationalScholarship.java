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
 * 中职奖学金名单
 * </p>
 *
 * @author hxy
 * @since 2023-05-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName("gjxt_secondary_vocational_scholarship")
public class GjxtSecondaryVocationalScholarship implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

//    @TableField("name")
    private String name;
    /**
     * 学号
     */
    @TableField("student_id")
    private String studentId;

    /**
     * 身份证号
     */
    @TableField("identity_id")
    private String identityId;

    /**
     * 委员会结果
     */
    @TableField("committee_results")
    private String committeeResults;

    /**
     * 小组结果
     */
    @TableField("group_results")
    private String groupResults;

    /**
     * 专家结果
     */
    @TableField("expert_result")
    private String expertResult;

    /**
     * 专家推优
     */
    @TableField("expert_excellent")
    private String expertExcellent;

    /**
     * 双10%学生
     */
    @TableField("double_ten_stu")
    private String doubleTenStu;

    /**
     * 成绩排名
     */
    @TableField("class_rank")
    private String classRank;

    /**
     * 综测排名
     */
    @TableField("test_rank")
    private String testRank;

    /**
     * 小组编号
     */
    @TableField("group_num")
    private String groupNum;

    /**
     * 小组名称
     */
    @TableField("group_name")
    private String groupName;

    /**
     * 学校id
     */
    @TableField("school_id")
    private Integer schoolId;

    /**
     * 学校名称
     */
//    @TableField("school_name")
    private String schoolName;

    /**
     * 所在省
     */
//    @TableField("province")
    private String province;

    /**
     * 所在市
     */
    @TableField("city")
    private String city;

    /**
     * 所在区县
     */
    @TableField("county")
    private String county;

    /**
     * 性别
     */
    @TableField("gender")
    private String gender;

    /**
     * 出生年月
     */
    @TableField("birth")
    private String birth;

    /**
     * 政治面貌
     */
    @TableField("political")
    private String political;

    /**
     * 民族
     */
    @TableField("nation")
    private String nation;

    /**
     * 学制
     */
    @TableField("length_school")
    private String lengthSchool;

    /**
     * 学生类型
     */
    @TableField("stu_type")
    private String stuType;

    /**
     * 联系电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 院系
     */
    @TableField("department")
    private String department;

    /**
     * 专业
     */
    @TableField("speciality")
    private String speciality;

    /**
     * 学年
     */
    @TableField("stu_year")
    private String stuYear;

    /**
     * 证书编号
     */
    @TableField("cert_number")
    private String certNumber;

    /**
     * 入学年月
     */
    @TableField("entry_year")
    private String entryYear;



@Override
public String toString() {
        return "GjxtSecondaryVocationalScholarship{" +
            "id=" + id +
            ", studentId=" + studentId +
            ", identityId=" + identityId +
            ", committeeResults=" + committeeResults +
            ", groupResults=" + groupResults +
            ", expertResult=" + expertResult +
            ", expertExcellent=" + expertExcellent +
            ", doubleTenStu=" + doubleTenStu +
            ", classRank=" + classRank +
            ", testRank=" + testRank +
            ", groupNum=" + groupNum +
            ", groupName=" + groupName +
            ", schoolId=" + schoolId +
            ", schoolName=" + schoolName +
            ", provice=" + province +
            ", city=" + city +
            ", county=" + county +
            ", gender=" + gender +
            ", birth=" + birth +
            ", political=" + political +
            ", nation=" + nation +
            ", lengthSchool=" + lengthSchool +
            ", stuType=" + stuType +
            ", phone=" + phone +
            ", department=" + department +
            ", speciality=" + speciality +
            ", stuYear=" + stuYear +
            ", certNumber=" + certNumber +
            ", entryYear=" + entryYear +
        "}";
        }
}
