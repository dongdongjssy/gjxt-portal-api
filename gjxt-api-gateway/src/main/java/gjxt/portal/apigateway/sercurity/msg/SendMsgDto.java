package gjxt.portal.apigateway.sercurity.msg;

import com.fasterxml.jackson.annotation.JsonFormat;
import gjxt.portal.apigateway.sercurity.enums.SmsTemplateTypeEnums;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiModel("发送短信消息实体类")
@Data
public class SendMsgDto implements Serializable {
    private static final long serialVersionUID = -378243746028628581L;

    @ApiModelProperty("消息模板和消息类型相关参数")
    private SmsTemplateTypeEnums smsTemplateTypeEnums;

    @ApiModelProperty("批次id")
    private String batchId;

    @ApiModelProperty("消息分类")
    private String type;

    @ApiModelProperty("消息分类")
    private Integer templateType;

    @ApiModelProperty("消息模板Id")
    private Long msgTplId;

    @ApiModelProperty("消息标题")
    private String title;

    @ApiModelProperty("发送时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime sendTime;

    @ApiModelProperty("接收时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime receiveTime;

    @ApiModelProperty("消息状态 1:未删除，0 已删除")
    private Integer status;

    @ApiModelProperty("发送方用户id")
    private Long sendUserId;

    @ApiModelProperty("发送方用户名")
    private String sendUserName;

    @ApiModelProperty("用户扩展id")
    private Long sendUserExtendId;

    @ApiModelProperty("角色id")
    private Long sendRoleId;

    @ApiModelProperty("接收方")
    private List<ReceiveUser> receiveUsers;

    @Data
    @ApiModel("接收方")
    public static class ReceiveUser {

        @ApiModelProperty("请求id")
        private String requestId;

        @ApiModelProperty("接收方用户名")
        private String name;

        @ApiModelProperty("接收方用户id")
        private Long userId;

        @ApiModelProperty("用户扩展id")
        private Long userExtendId;

        @ApiModelProperty("手机号码，短信发送时必填")
        private String phone;

        @ApiModelProperty("角色id")
        private Long roleId;

        @ApiModelProperty("短信内容")
        private Map<String, String> smsContent;

        public ReceiveUser buildRequestId(String requestId){
            this.requestId = requestId;
            return this;
        }

        public ReceiveUser buildPhone(String phone){
            this.phone = phone;
            return this;
        }
        public ReceiveUser smsContentPut(String key,String value){
            if(smsContent==null){
                smsContent = new HashMap<>();
            }
            smsContent.put(key,value);
            return this;
        }
        public ReceiveUser buildName(String name){
            this.name = name;
            return this;
        }
        public ReceiveUser buildUserId(Long userId){
            this.userId = userId;
            return this;
        }
        public ReceiveUser buildUserExtendId(Long userExtendId){
            this.userExtendId = userExtendId;
            return this;
        }
        public ReceiveUser buildRoleId(Long roleId){
            this.roleId = roleId;
            return this;
        }
        public ReceiveUser smsContentPutAll(Map<String, String> addsmsContent){
            if(addsmsContent==null || addsmsContent.size()==0){
                return this;
            }
            if(this.smsContent==null){
                this.smsContent = new HashMap<>();
            }
            smsContent.putAll(addsmsContent);
            return this;
        }
    }
    public void addReceiveUsers(ReceiveUser receiveUser){
        if(receiveUsers==null){
            receiveUsers = new ArrayList<>();
        }
        receiveUsers.add(receiveUser);
    }
}
