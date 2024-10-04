package gjxt.portal.apigateway.sercurity.controller;

import gjxt.portal.apigateway.sercurity.domain.ResultCode;
import gjxt.portal.apigateway.sercurity.domain.ResultJson;
import gjxt.portal.apigateway.sercurity.domain.auth.ClientUser;
import gjxt.portal.apigateway.sercurity.domain.auth.ResponseUserToken;
import gjxt.portal.apigateway.sercurity.domain.auth.UserDetail;
import gjxt.portal.apigateway.sercurity.dto.LoginDto;
import gjxt.portal.apigateway.sercurity.enums.SmsTemplateTypeEnums;
import gjxt.portal.apigateway.sercurity.msg.SendMsgDto;
import gjxt.portal.apigateway.sercurity.msg.SendMsgFeignService;
import gjxt.portal.apigateway.sercurity.msg.SendRealTimeResultVo;
import gjxt.portal.apigateway.sercurity.dto.VerifyImgeResponse;
import gjxt.portal.apigateway.sercurity.dto.VerifyImgeVo;
import gjxt.portal.apigateway.sercurity.exception.CustomException;
import gjxt.portal.apigateway.sercurity.redis.RedisKey;
import gjxt.portal.apigateway.sercurity.redis.RedisUtil;
import gjxt.portal.apigateway.sercurity.service.AuthService;
import gjxt.portal.apigateway.sercurity.utils.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * 登录
 *
 * @author hxy
 * @date 2023/4/24
 */
@RestController
@RequestMapping("portal")
public class LoginController {

    @Resource
    private JwtConfig jwtCfg;

    private final AuthService authService;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${login.SMSVerification.loginInputMax:5}")
    private  Integer  loginInputMax;

    @Setter
    @Value("${login.SMSVerification.enable:false}")
    private boolean loginSMSVerificationEnable;

    @Setter
    @Value("${login.SMSVerification.maxInactiveInterval:300}")
    private Long loginSMSVerificationMaxInactiveInterval;

    @Setter
    @Value("${login.SMSVerification.allowResetTime:60}")
    private Integer loginSMSVerificationAllowResetTime;

    @Setter
    @Value("${login.SMSVerification.safetyEnable:false}")
    private boolean loginSMSVerificationSafetyEnable;

    @Setter
    @Value("${login.session.maxInactiveInterval:1800}")
    private Integer loginSessionMaxInactiveInterval;

    @Setter
    @Value("${login.SMSVerification.dayTotalNum:30}")
    private  Integer  loginSMSVerificationDayTotalNum;

    private static final String VERIFYIMAGE_DEFAULT_IMG_PATH = "image/verify_image/300_200/";

    private static final String VERIFYIMAGE_TEMP_IMG_PATH = "image/verify_image/300_200/temp/";

    public static final String DEFAULT_MKDIR = "auth_file_temp";
    private static final Long IMG_CACHE_EX_TIME = 120L;

    @Autowired
    private SendMsgFeignService sendMsgFeignService;

    @Autowired
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/sign")
    @ApiOperation(value = "用户注册")
    public ResultJson sign(@RequestBody ClientUser user) {
        //todo 需要验证验证码

        if (StringUtils.isAnyBlank(user.getPhone(), user.getPassword(), user.getIdNumber())) {
            throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "手机号、密码和身份证号都不能为空"));
        }

        UserDetail register = authService.register(user);
        return ResultJson.ok(register.getUser().getId());
    }


    @PostMapping(value = "/login")
    public ResultJson login(@RequestBody LoginDto loginDto) {
        //todo 需要验证验证码
        ResponseUserToken login = authService.login(loginDto.getPhone(), loginDto.getPassword());
        return ResultJson.ok(login);
    }

    @GetMapping(value = "/test")
    public ResultJson test() {
        return ResultJson.ok(2);
    }


    @GetMapping(value = "/logout")
    @ApiOperation(value = "登出", notes = "退出登陆")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public ResultJson logout(HttpServletRequest request) {
        String token = request.getHeader(jwtCfg.getHeader());
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        authService.logout(token);
        return ResultJson.ok();
    }

    @GetMapping(value = "/user")
    @ApiOperation(value = "根据token获取用户信息", notes = "根据token获取用户信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public ResultJson getUser(HttpServletRequest request) {
        String token = request.getHeader(jwtCfg.getHeader());
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        return ResultJson.ok(userDetail);
    }

    @GetMapping(value = "/validate")
    @ApiOperation(value = "调用API是验证是否token有效", notes = "调用API是验证是否token有效")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public ResultJson validateToken(HttpServletRequest request) {
        String token = request.getHeader(jwtCfg.getHeader());
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        if (userDetail == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }

        return ResultJson.ok();
    }


    @GetMapping(value = "/refresh")
    @ApiOperation(value = "刷新token")
    public ResultJson refresh(HttpServletRequest request) {
        String token = request.getHeader(jwtCfg.getHeader());
        String refreshToken = request.getHeader(jwtCfg.getRefreshTokenHeader());
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED, "缺少access token信息");
        }
        if (refreshToken == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST, "缺少refresh token信息");
        }

        ResponseUserToken response = authService.refresh(token, refreshToken);
        if (response == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST, "无效token或token已过期");
        } else {
            return ResultJson.ok(response);
        }
    }

    /**
     * 获取验证码
     * @return
     * @throws Exception
     * @author pangxianhe
     * @date 2020年1月2日
     */
    @RequestMapping(value = "/getImageVerifyCode", method = RequestMethod.POST)
    @ApiOperation(value = "生成图片验证码", notes = "生成图片验证码")
    public ResultJson getImageVerifyCode(@RequestBody @Valid LoginDto loginParams) {
        boolean errFlag = false;
        if(!errFlag){
            String tel = loginParams.getPhone();
            Object o = redisUtil.getObjectByKey(RedisKey.LONG_ACCOUNT_LOCK + tel);
            if (o != null){
                redisUtil.delete(RedisKey.LONG_INPUT_COUNT + tel);
                return ResultJson.failure("已连续"+loginInputMax+"次账号或密码输入错误，该账号已被锁定"+(loginInputMax * 6)+"分钟，请稍后再试");
            }

            String dayTotalNumRediskey = RedisKey.VERIFICATIONCODE_DAY_TOTAL_NUM+tel;
            if (loginSMSVerificationEnable) {
                Object num =redisUtil.getObjectByKey(dayTotalNumRediskey);
                if (num != null && ((Integer) num) >= loginSMSVerificationDayTotalNum) {
                    return ResultJson.failure("单日获取验证码次数超过上限（" + loginSMSVerificationDayTotalNum + "次）");
                }
            }
        }

        // 读取图库目录
        String key = RedisKey.generateKey(loginParams.getUuid(),RedisKey.HAD_OVERRIDE_DATA);
        String redisKey = getVerificationCodePicRedisKey(key);

        File targetFile = ResourceFileUtils.queryFileTargetFile(VERIFYIMAGE_DEFAULT_IMG_PATH,
                DEFAULT_MKDIR+"/imageverifycode/",key,70,"jpg");
        if(targetFile==null){
            return ResultJson.failure(ResultCode.CODE_ERROR,"获取图片验证码失败");
        }
        File tempImgFile = ResourceFileUtils.queryFileTargetFile(VERIFYIMAGE_TEMP_IMG_PATH,
                DEFAULT_MKDIR+"/imageverifycode/temp/",key,10,"png");
        if(tempImgFile==null){
            return ResultJson.failure(ResultCode.CODE_ERROR,"获取图片验证码失败");
        }
        // 根据模板裁剪图片
        Map<String, Object> resultMap = null;
        try{
            resultMap = VerifyImageUtil.pictureTemplatesCut(tempImgFile, targetFile);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //删除临时模版文件
            try{
                if(targetFile!=null){
                    FileUtils.deleteQuietly(targetFile);
                }
                if(tempImgFile!=null){
                    FileUtils.deleteQuietly(tempImgFile);
                }
            }catch (Exception e){

            }
        }
        if(resultMap==null){
            return ResultJson.failure("获取图片验证码错误");
        }
        String xWidth =resultMap.get("xWidth").toString();
        //String chenckMoveid = IdWorker.get32UUID();
        resultMap.remove("xWidth");
        redisUtil.setValueExpireSeconds(redisKey, xWidth,IMG_CACHE_EX_TIME);
        return ResultJson.ok(resultMap);
    }

    public String getVerificationCodePicRedisKey(String key){
        String redisKey = RedisKey.VERIFICATIONCODE_PIC+key;
        return redisKey;
    }

    @RequestMapping(value = "/validateImageVerifyCode", method = RequestMethod.POST)
    @ApiOperation(value = "验证图片验证码", notes = "验证图片验证码")
    public ResultJson validateImageVerifyCode(@RequestBody @Valid VerifyImgeVo verifyImgeVo){
        // 校验滑块随机数
        LoginDto loginParams = verifyImgeVo.getLoginDto();
        Integer type = 2;
        if(StringUtils.isEmpty(loginParams.getIdNumber())){
            type = 1;
        }
        boolean errFlag = false;
        String key = RedisKey.generateKey(loginParams.getUuid(), RedisKey.HAD_OVERRIDE_DATA);
        String redisKey = getVerificationCodePicRedisKey(key);
        Integer xIndex = verifyImgeVo.getVerifykey();
        // 获取readis缓存的随机数
        VerifyImgeResponse verifyImgeResponse = new VerifyImgeResponse();
        Object x_index_orld = redisUtil.getObjectByKey(redisKey);
        if (Objects.isNull(x_index_orld)) {
            verifyImgeResponse.setFail(true);
            verifyImgeResponse.setCode(ResultCode.OPERATION_not_ALLOWED.getCode());
            verifyImgeResponse.setMessage("验证超时，请根据提示重新操作");
            ResultJson imageVerifyCode = getImageVerifyCode(loginParams);
            verifyImgeResponse.setResultMap((Map<String, Object>) imageVerifyCode.getData());
            return  ResultJson.ok(verifyImgeResponse);
        } else {
            Double dMoveLength = Double.valueOf(x_index_orld.toString());
            Double xWidth = Double.valueOf(xIndex);
            if (Math.abs(xWidth - dMoveLength) > 10) {
                //验证不通过，这个地方需要注意一下，不管他是否验证通过，都需要重置一下图片和坐标位置，避免暴力破解
                redisUtil.delete(redisKey);
                verifyImgeResponse.setFail(true);
                verifyImgeResponse.setCode(ResultCode.OPERATION_not_ALLOWED.getCode());
                verifyImgeResponse.setMessage("验证失败，请根据提示重新操作");
                ResultJson imageVerifyCode = getImageVerifyCode(loginParams);
                verifyImgeResponse.setResultMap((Map<String, Object>) imageVerifyCode.getData());
                return  ResultJson.ok(verifyImgeResponse);
            } else {
                //验证通过
                redisUtil.delete(redisKey);
                if(!errFlag){
                    ResultJson<String> stringResponseResult = userPassSendVerificationCode(verifyImgeVo.getLoginDto(), type);
                    verifyImgeResponse.setFail(false);
                    verifyImgeResponse.setCode(ResultCode.OK.getCode());
                    //TODO 为了开发 吧验证码返回了
                    verifyImgeResponse.setMessage(stringResponseResult.getData().split(":")[0]);
                    String tel = verifyImgeVo.getLoginDto().getPhone();
                    if(loginSMSVerificationSafetyEnable || type==2){
                        if(type==2){
                            verifyImgeResponse.setMessage("短信已发送至账号"+tel.substring(tel.length()-4)+"所在手机，如果手机号不存在，则无法接收验证码");
                        }else{
                            verifyImgeResponse.setMessage("短信已发送至账号"+tel.substring(tel.length()-4)+"所在手机，如果账号或者密码错误，则无法接收验证码");
                        }
                    }else{
//                        verifyImgeResponse.setMessage("短信已发送至尾号“"+tel.substring(tel.length()-4)+"”手机");
                    }
                    if(stringResponseResult.getCode()!=200){
                        verifyImgeResponse.setFail(true);
                        verifyImgeResponse.setCode(stringResponseResult.getCode());
                        verifyImgeResponse.setMessage(stringResponseResult.getMsg());
                        if(ResultCode.FAILED_CODE_DAY_TOTAL_NUM.getCode()!=stringResponseResult.getCode()){
                            ResultJson imageVerifyCode = getImageVerifyCode(loginParams);
                            verifyImgeResponse.setResultMap((Map<String, Object>) imageVerifyCode.getData());
                        }
                    }
                }else{
                    verifyImgeResponse.setFail(false);
                    verifyImgeResponse.setCode(ResultCode.OK.getCode());
                    String identityLabel = verifyImgeVo.getLoginDto().getPhone();
                    if(type==2){
                        if(identityLabel.length()==11){
                            verifyImgeResponse.setMessage("短信已发送至尾号“"+identityLabel.substring(identityLabel.length()-4)+"”手机，如果手机号不存在，则无法接收验证码");
                        }else{
                            verifyImgeResponse.setMessage("短信已发送至账号"+identityLabel+"所在手机，如果手机号不存在，则无法接收验证码");
                        }
                    }else{
                        if(identityLabel.length()==11){
                            verifyImgeResponse.setMessage("短信已发送至尾号“"+identityLabel.substring(identityLabel.length()-4)+"”手机，如果手机号不存或者密码错误，则无法接收验证码");
                        }else{
                            verifyImgeResponse.setMessage("短信已发送至账号"+identityLabel+"所在手机，如果账号或者密码错误，则无法接收验证码");
                        }
                    }
                }
                return  ResultJson.ok(verifyImgeResponse);
            }
        }
    }

    public static  String getVerificationCodeKey(int type,String tel){
        if(type!=1){
            return RedisKey.VERIFICATIONCODE_RETRIEVE+tel;
        }else{
            return RedisKey.VERIFICATIONCODE_LOGIN+tel;
        }
    }

    public ResultJson<String> userPassSendVerificationCode(LoginDto loginParams,int type){
        String tel = loginParams.getPhone();
        String rediskkey = getVerificationCodeKey(type,tel);
        String dayTotalNumRediskey = RedisKey.VERIFICATIONCODE_DAY_TOTAL_NUM+tel;
        Long expire = redisUtil.getExpire(rediskkey);
        if(expire!=null && expire> 0 && (loginSMSVerificationMaxInactiveInterval-expire)<loginSMSVerificationAllowResetTime){
            return ResultJson.ok("发送验证码成功");
        }
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * Math.pow(10, 6 - 1)));
        System.out.println("短信验证码：" + code);
        //判断用户验证码是否正确
        ResultJson<SendRealTimeResultVo> responseResult = null;
        if (loginSMSVerificationEnable) {
            Object num = redisUtil.getObjectByKey(dayTotalNumRediskey);
            if(num!=null && ((Integer)num)>=loginSMSVerificationDayTotalNum){
                return ResultJson.failure("单日获取验证码次数超过上限（"+loginSMSVerificationDayTotalNum+"次）");
            }
            ClientUser userLoginInfo = validateUserLoginInfo(loginParams, type);
            //todo 发送验证码
            responseResult = sendMsgFeignService.sendRealTimeMsg(buildSendMsgDtoByVerificationCode(userLoginInfo,code,type));
            if(responseResult.isStatus() && responseResult.getBody()!=null){
                SendRealTimeResultVo body = responseResult.getBody();
                if(body.isAllSuccess()){
                    redisUtil.setValueExpireSeconds(rediskkey, code, loginSMSVerificationMaxInactiveInterval);
                    verificationCodeIncrement(dayTotalNumRediskey);
                    return ResultJson.ok("【已发送至尾号“"+tel.substring(tel.length()-4)+"”手机】");
                }else{
                    return ResultJson.failure("发送验证码失败");
                }
            }else{
                return ResultJson.failure("发送验证码失败");
            }
        }else{
            return ResultJson.ok("短信已发送至尾号“"+tel.substring(tel.length()-4)+"”手机");
        }
    }

    public SendMsgDto buildSendMsgDtoByVerificationCode(ClientUser userLoginInfo, String code, Integer type){
        SendMsgDto sendMsgDto = new SendMsgDto();
        SmsTemplateTypeEnums smsTemplateTypeEnums = SmsTemplateTypeEnums.VERIFICATION_CODE_LOGIN;
        if(type!=1){
            smsTemplateTypeEnums = SmsTemplateTypeEnums.RETRIEVE_PASSWORD;
        }
        sendMsgDto.setBatchId(StringUtil.uuid());
        sendMsgDto.setSmsTemplateTypeEnums(smsTemplateTypeEnums);
        sendMsgDto.setSendUserId(userLoginInfo.getId());
        sendMsgDto.setSendTime(LocalDateTime.now());
        sendMsgDto.setSendUserName(userLoginInfo.getUsername());
        sendMsgDto.setTitle(smsTemplateTypeEnums.getDes());
        SendMsgDto.ReceiveUser receiveUser = new SendMsgDto.ReceiveUser().
                buildUserId(userLoginInfo.getId()).
                buildName(userLoginInfo.getUsername()).
                buildPhone(userLoginInfo.getPhone()).
                buildRequestId(StringUtil.uuid()).
                smsContentPut("verificationCode",code);
        sendMsgDto.addReceiveUsers(receiveUser);
        return sendMsgDto;
    }

    public void verificationCodeIncrement(String key){
        if(redisUtil.hasKey(key)){
            redisUtil.increment(key);
        }else{
            Long nowToNextDaySeconds = DateUtils.getNowToNextDaySeconds();
            redisUtil.setValueExpireSeconds(key,"1",nowToNextDaySeconds);
        }
    }



    public ClientUser  validateUserLoginInfo(LoginDto loginParams,int type){
        //第一步 查询该账号的密码盐（多种）
        ClientUser userLoginInfo = authService.selectUserByPhone(loginParams.getPhone());
        if (null == userLoginInfo && type!=1) {
            inputCount(loginParams.getPhone());
            throw new CustomException(ResultJson.failure(ResultCode.USER_NOT_EXISTS));
        }else {
            if (userLoginInfo.getStatus()!=0) {
                throw new CustomException(ResultJson.failure("账号已被禁用"));
            }

            if(StringUtil.isEmpty(userLoginInfo.getPhone())){
                inputCount(loginParams.getPhone());
                throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR));
            }

            if (StringUtil.isEmpty(loginParams.getPassword())) {
                inputCount(loginParams.getPhone());
                throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR));

            }else {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String encode = encoder.encode(loginParams.getPassword());
                if(!encoder.matches(userLoginInfo.getPassword(),encode)){
                    inputCount(loginParams.getPhone());
                    throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR));
                }
            }
        }
        redisUtil.delete(RedisKey.LONG_INPUT_COUNT + loginParams.getPhone());
        return userLoginInfo;
    }

    /**
     * 记录错误次数
     * @param identityLabel
     */
    private void inputCount(String identityLabel){
        //记录校验账号失败次数
        Object o = redisUtil.getObjectByKey(RedisKey.LONG_INPUT_COUNT + identityLabel);
        if (o == null){
            redisUtil.set(RedisKey.LONG_INPUT_COUNT + identityLabel,"1");
        }else {
            if ((Integer) o + 1 >= loginInputMax){
                redisUtil.setValueExpireSeconds(RedisKey.LONG_ACCOUNT_LOCK+identityLabel,identityLabel,loginInputMax * 6 * 60);
            }else {
                redisUtil.set(RedisKey.LONG_INPUT_COUNT + identityLabel,((Integer)o + 1)+"");
            }
        }
    }

}
