package gjxt.portal.apigateway.sercurity.msg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel("消息发送")
public class MessageVo implements Serializable {

    private static final long serialVersionUID = -64930776618722553L;

    @ApiModelProperty("消息分类")
    private String  type;

    @ApiModelProperty("消息标题")
    private String title;

    @ApiModelProperty("消息内容")
    private Object content;

    @ApiModelProperty("短信内容")
    private List<String> smsContent;

    @ApiModelProperty("发送时间")
    private LocalDateTime sendTime;

    @ApiModelProperty("接收时间")
    private LocalDateTime receiveTime;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("消息状态 1:未删除，0 已删除")
    private Integer status;

    @ApiModelProperty("发送方用户id")
    private Integer sendUserId;

    @ApiModelProperty("发送方用户名")
    private String snedUserName;

    @ApiModelProperty("接收方")
    private List<ReceiveUser> receiveUsers;

    private Integer msgId;

    private Integer unread;

    @ApiModelProperty("模板信息")
    private String  stationMessageId;

    @ApiModelProperty("消息模板备注")
    private String  stationMessageTitle;

    @ApiModelProperty("帮助url")
    private String helpUrl;

    @ApiModelProperty("角色所属端高校或者中职")
    private Integer state;


    @Data
    @ApiModel("接收方")
    public static class ReceiveUser implements Serializable{

        private static final long serialVersionUID = -2112176070845795364L;
        @ApiModelProperty("接收方用户名")
        private String name;

        @ApiModelProperty("接收方用户id")
        private Integer userId;

        @ApiModelProperty("手机号码，短信发送时必填")
        private String phone;

        @ApiModelProperty("用户扩展id")
        private Long userExtendId;

        @ApiModelProperty("角色名称")
        private String roleNames;

        @ApiModelProperty("角色ids")
        private String roleIds;

        @ApiModelProperty("角色所属端高校或者中职")
        private Integer state;

        @ApiModelProperty("消息唯一key ")
        private String xxukey;

        @ApiModelProperty("消息唯一描述 ")
        private String xxukeydes;
    }
}