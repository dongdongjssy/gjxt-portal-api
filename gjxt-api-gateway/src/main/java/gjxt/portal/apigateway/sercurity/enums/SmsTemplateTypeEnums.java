package gjxt.portal.apigateway.sercurity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
/**
 * 【国奖评审系统】
 */
public enum SmsTemplateTypeEnums {
    /**
     * 2110141126360090
     */
    VERIFICATION_CODE_LOGIN(1001,"verification_code_login",
            "您正在登陆国家奖学金评审系统，验证码：${verificationCode}，5分钟内有效，为确保信息安全，请勿泄漏。如非本人操作，请忽略本短信。",
            "验证码登陆","验证码登陆",
            "verification_code_login",""),
    /**
     * 2204241713140386
     */
    RETRIEVE_PASSWORD(1010,"retrieve_password",
            "您正在重置用户密码，验证码：${verificationCode}，5分钟内有效，为确保信息安全，请勿泄漏。如非本人操作，请忽略本短信。",
            "找回密码","找回密码",
            "retrieve_password",""),
    /**
     * 2204241722100388
     */
    SCHOOL_OPERATOR_SUBMIT_STUDENT_AND_REPORT(1020,
            "school_operator_submit_student_and_report",
            "学校操作人员已提交国奖申报名单及评审报告，请及时审核。",
            "学校操作人员已提交国奖申报名单及评审报告",
            "学校操作人员已提交国奖申报名单及评审报告，系统发送短信给学校审核人员",
            "school_operator_submit_student_and_report","学校审核人员"),
    /**
     * 2204241722570391
     */
    SCHOOL_AUDITOR_BACK_STUDENT(1030,
            "school_auditor_back_student",
            "学校审核人员已退回国奖申报名单，请及时处理。",
            "学校审核人员已退回国奖申报名单",
            "学校审核人员将国奖申报名单退回，系统发送短信给学校操作人员",
            "school_auditor_back_student","学校操作人员"),
    /**
     * 2204241722330389
     */
    SCHOOL_OPERATOR_SUBMIT_STUDENT_INFO_ADD(1040,
            "school_operator_submit_student_info_add",
            "学校操作人员已增补提交国奖申报名单，请及时审核。",
            "学校操作人员已增补提交国奖申报名单",
            "学校操作人员增补提交国奖申报名单，系统发送短信给学校审核人员",
            "school_operator_submit_student_info_add","学校操作人员"),
    /**
     * 2204241722450390
     */
    SCHOOL_AUDITOR_BACK_REPORT(1050,
            "school_auditor_back_report",
            "学校审核人员已退回国奖评审报告，请及时处理。",
            "学校审核人员已退回国奖评审报告",
            "学校审核人员将国奖评审报告退回，系统发送短信给学校操作人员",
            "school_auditor_back_report","学校操作人员"),
    /**
     * 2204241723090392
     */
    SCHOOL_SUBMIT_REPORT_TO_DEPART(1060,
            "school_submit_report_to_depart",
            "${schoolName}已提交国奖评审报告，请及时审核。",
            "学校提交国奖评审报告至上级部门",
            "学校审核人员将本校国奖评审报告提交至上级主管部门审核，系统发送短信给该校上级主管部门业务管理员",
            "school_submit_report_to_depart","省/市/县级业务管理员"),
    /**
     * 2204241723330393
     */
    SCHOOL_SUBMIT_STUDENT_TO_DEPART(1070,
            "school_submit_student_to_depart",
            "${schoolName}已提交国奖申报名单，请及时审核。",
            "学校提交国奖申报名单至上级部门",
            "学校审核人员将本校国奖申报名单提交至上级主管部门审核，系统发送短信给该校上级主管部门业务管理员",
            "school_submit_student_to_depart","省/市/县级业务管理员"),
    /**
     * 2204241723450394
     */
    DEPART_BACK_REPORT(1080,
            "depart_back_report",
            "${nodeName}已退回国奖评审报告，请及时处理。",
            "上级单位将评审报告审核为退回",
            "上级主管部门业务管理员将学校国奖评审报告退回，系统发送短信给学校审核人员和操作人员",
            "depart_back_report","学校审核人员和操作人员"),
    /**
     * 2204241723550395
     */
    DEPART_BACK_STUDENT_INFO(1090,
            "depart_back_student_info",
            "${nodeName}已退回国奖申报名单，请及时处理。",
            "上级单位退回申报名单内的学生",
            "上级主管部门业务管理员将学校国奖申报名单退回，系统发送短信给学校审核人员和操作人员",
            "depart_back_student_info","学校审核人员和操作人员"),
    /**
     * 2204241713140386
     */
    RESET_PASSWORD(1100,
            "reset_password",
            "您正在重置用户密码，验证码：${verificationCode}，5分钟内有效，为确保信息安全，请勿泄漏。如非本人操作，请忽略本短信。",
            "密码重置",
            "用户重置密码时，需接收系统短信验证码",
            "reset_password",""),
    /**
     * 2204241721170387
     */
    INITIALIZE_PASSWORD(1110,
            "initialize_password",
            "欢迎使用国家奖学金评审系统，您的用户信息已创建成功，账号：${phone}，初始密码：${password}。请及时登录系统修改。",
            "创建用户",
            "成功创建各级主管部门、学校，以及评审工作小组、专家账号时，系统为新用户发送账号信息。",
            "initialize_password",""),
    /**
     * 2205061520041002
     */
    RELEASE_QUOTA(1120,"release_quota","${sourceNodeName}已将本年度国家奖学金名额分配至${targetNodeName}，请及时处理。",
            "下发名额","当中央级、省级和地市级成功为下级部门分配国奖名额时，系统发送短信给对应下级单位",
            "release_quota","业务管理员/学校操作人员"),
    /**
     * 2205061521401003
     */
    OPEN_EXPERT_REVIEW_ZJ(1130,"open_expert_review_zj","本年度${nodeName}国家奖学金评审工作已开始，${extMsg}",
                          "开启专家评审发送短信给专家","专家账号是完全新增的账号时：您被选为评审专家，账号：${phone}，初始密码：${password}，请及时开展评审并修改密码。评审截止时间${expertReviewDeadline}。;现存账号添加为专家用户时：您被选为评审专家，请及时开展评审。评审截止时间${expertReviewDeadline}。",
                          "open_expert_review_zj","专家"),
    /**
     * 2205192139560825
     */
    REQUIRE_STUFF_BACK_SEND_TO_SCHOOL(1140,
            "require_stuff_back_send_to_school",
            "本年度${sourceNodeName}国家奖学金评审工作中，专家判定你校${studentName}同学需要补充材料，请及时处理。","需补材料退回发送信息给学校",
            "省级专家开展国奖评审工作时，判定某学生需补材料，系统发送短信给该学生所在学校。",
            "require_stuff_back_send_to_school",
            "学校审核人员&学校操作人员"),
    /**
     * 2205192141070826
     */
    REQUIRE_STUFF_BACK_SEND_TO_DEPART(1150,
            "require_stuff_back_send_to_depart",
            "本年度${sourceNodeName}国家奖学金评审工作中，专家判定你辖区${schoolName}的${studentName}同学需要补充材料，请及时关注并督促处理。","需补材料退回发送信息给学校管辖区域",
            "省级专家开展国奖评审工作时，判定某学生需补材料，系统发送短信给该学生所在学校管辖区域。",
            "require_stuff_back_send_to_depart",
            "省/市/县级业务管理员"),
    /**
     * 2205192142370827
     */
    REQUIRE_STUFF_BACK_RESUBMIT_SEND_TO_EXPERT(1160,
            "require_stuff_back_resubmit_send_to_expert",
            "${schoolName}已将${studentName}同学的材料补充完毕，并已重新提交，请及时处理。","需补材料学生补充材料重新提交发送短信给专家",
            "需补材料学生补充材料重新提交发送短信给专家",
            "require_stuff_back_resubmit_send_to_expert",
            "省级评审专家"),
    /**
     * 2205192144170828
     */
    REQUIRE_STUFF_BACK_RESUBMIT_SEND_TO_DEPART(1170,
            "require_stuff_back_resubmit_send_to_depart",
            "你辖区${schoolName}已将${studentName}同学的材料补充完毕，并已重新提交至${nodeLevel}评审专家处，请及时关注。","需补材料学生补充材料重新提交发送短信给管辖部门",
            "需补材料学生补充材料重新提交发送短信给管辖部门",
            "require_stuff_back_resubmit_send_to_depart",
            "省/市/县级业务管理员"),
    /**
     * 2205192145100829
     */
    REQUIRE_STUFF_BACK_REPLACE_SEND_TO_DEPART(1180,
            "require_stuff_back_replace_send_to_depart",
            "你辖区${schoolName}将需要补充材料的${oldStudentName}同学替换为${newStudentName}同学，并已重新提交，请及时重新初审。","需补材料学生补充材料替换学生发送短信给管辖部门",
            "需补材料学生补充材料替换学生发送短信给管辖部门",
            "require_stuff_back_replace_send_to_depart",
            "省/市/县级业务管理员"),
    /**
     * 2205192145530830
     */
    REQUIRE_STUFF_BACK_REPLACE_SEND_TO_EXPERT(1190,
            "require_stuff_back_replace_send_to_expert",
            "${schoolName}已将需要补充材料的${oldStudentName}同学替换为${newStudentName}同学，并已重新提交，请及时处理。","需补材料学生补充材料替换学生发送短信给专家",
            "需补材料学生补充材料替换学生发送短信给专家",
            "require_stuff_back_resubmit_send_to_expert",
            "省级评审专家"),

    OCR(1200, "school_students_ocr_complete", "系统已完成你校于${entryTime}录入的国奖申报材料的OCR识别工作，请及时处理。",
            "学校ocr识别完成发送消息“@”为XX年XX月XX日 XX:XX", "学校ocr识别完成发送消息", "school_students_ocr_complete", "学校操作人员"),

    SCHOLARSHIP_SUBMIT(1210, "scholarship_submit", "学校操作人员已经提交资金发放名单，请及时处理。",
            "学校操作人员已经提交资金发放名单，请及时处理。", "学校操作人员提交资金发放名单", "scholarship_submit", "学校操作人员");

    private Integer templateType;
    private String messgeType;
    private String template;
    private String des;
    private String scenes;
    private String permissionCode;
    private String receiver;

    public static SmsTemplateTypeEnums getByName(String name){
        for (SmsTemplateTypeEnums value : SmsTemplateTypeEnums.values()) {
            if(value.name().equals(name)){
                return value;
            }
        }
        return null;
    }
}
