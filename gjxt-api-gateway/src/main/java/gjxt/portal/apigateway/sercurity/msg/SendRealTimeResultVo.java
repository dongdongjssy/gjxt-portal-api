package gjxt.portal.apigateway.sercurity.msg;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SendRealTimeResultVo {

   @ApiModelProperty("失败列表")
   private List<Result>  failList = new ArrayList<>();

    @ApiModelProperty("成功列表")
    private List<Result>  successList =new ArrayList<>();

    @Data
    @ApiModel("接收方")
    public static class Result {
        @ApiModelProperty("请求id")
        private String requestId;
        @ApiModelProperty("消息")
        private String message;
        @ApiModelProperty("消息 0 未完成 1已经完成")
        private Integer sendStatus;
    }
    public boolean isAllSuccess(){
        return failList.isEmpty();
    }
}
