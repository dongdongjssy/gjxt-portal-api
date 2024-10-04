package gjxt.portal.apigateway.sercurity.msg;

import gjxt.portal.apigateway.sercurity.domain.ResultJson;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: LaZy
 * @Date: 2021/11/8 01:03
 * @Description:
 */
@FeignClient(value = "message-center",contextId = "message-feign-SendMsgFeignService")
@RequestMapping(value = "/v1/message/",headers={"encrypt=${security.key:n6ZZxMyloQY1oarPNXA96A}"})
public interface SendMsgFeignService {

    @PostMapping(value = "/sendMessage")
    ResultJson<Boolean> sendMessage(@RequestBody MessageVo messageVo);

    @PostMapping("sendMsg/sendRealTimeMsg")
    @ApiOperation("发送实时消息")
    ResultJson<SendRealTimeResultVo> sendRealTimeMsg(@RequestBody SendMsgDto sendMsgDto);

    @PostMapping("sendMsg/sendNonRealTimeMsg")
    @ApiOperation("发送实时消息")
    ResultJson<Boolean> sendNonRealTimeMsg(@RequestBody SendMsgDto sendMsgDto);
}