package gjxt.portal.backgateway.sercurity.controller;

import gjxt.portal.backgateway.sercurity.domain.ResultCode;
import gjxt.portal.backgateway.sercurity.domain.ResultJson;
import gjxt.portal.backgateway.sercurity.domain.auth.ResponseUserToken;
import gjxt.portal.backgateway.sercurity.domain.auth.User;
import gjxt.portal.backgateway.sercurity.domain.auth.UserDetail;
import gjxt.portal.backgateway.sercurity.exception.CustomException;
import gjxt.portal.backgateway.sercurity.service.AuthService;
import gjxt.portal.backgateway.sercurity.utils.JwtConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author goose
 * createAt: 2019/4/1
 */

@RestController
@Api(description = "登陆注册及刷新token")
@RequestMapping("/api/v1/client")
@CrossOrigin(allowCredentials="true",maxAge = 3600)
public class AuthController {

    private final AuthService authService;
    
    @Resource
    private JwtConfig jwtCfg;
    

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    /**
     * 
     * @param user
     * userName password roleName 
     * @return
     */
    @PostMapping(value = "/sign")
    @ApiOperation(value = "用户注册")
    public ResultJson sign(@RequestBody User user) {
        if (StringUtils.isAnyBlank(user.getUsername(), user.getPassword())) {
        	throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "用户名或密码为空"));
        }
        UserDetail register = authService.register(user);
        return ResultJson.ok(register.getUser().getUserId());
    }
  
    @PostMapping(value = "/login")
    @ApiOperation(value = "登陆", notes = "登陆成功返回token")
    public ResultJson<ResponseUserToken> login(@RequestBody User user){
    	
        final ResponseUserToken response = authService.login(user.getUsername(), user.getPassword());
        return ResultJson.ok(response);
    }

    @GetMapping(value = "/logout")
    @ApiOperation(value = "登出", notes = "退出登陆")
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
    public ResultJson logout(HttpServletRequest request){
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
    public ResultJson getUser(HttpServletRequest request){
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
    public ResultJson validateToken(HttpServletRequest request){
    	String token = request.getHeader(jwtCfg.getHeader());
    	if(token == null) {
    		return ResultJson.failure(ResultCode.UNAUTHORIZED);
    	}
    	UserDetail userDetail = authService.getUserByToken(token);
    	if(userDetail == null) {
    		return ResultJson.failure(ResultCode.UNAUTHORIZED);
    	}
 
        return ResultJson.ok();
    }

    
    @GetMapping(value = "/refresh")
    @ApiOperation(value = "刷新token")
	public ResultJson refresh(HttpServletRequest request) {
        String token = request.getHeader(jwtCfg.getHeader());
        String refreshToken = request.getHeader(jwtCfg.getRefreshTokenHeader());
    	if(token == null) {
    		return ResultJson.failure(ResultCode.UNAUTHORIZED, "缺少access token信息");
    	}
    	if(refreshToken == null) {
    		return ResultJson.failure(ResultCode.BAD_REQUEST, "缺少refresh token信息");
    	}    	
    	
        ResponseUserToken response = authService.refresh(token, refreshToken);
        if(response == null) {
            return ResultJson.failure(ResultCode.BAD_REQUEST, "无效token或token已过期");
        } else {
            return ResultJson.ok(response);
        }
    }
    
	@PostMapping("/updatePwd")
	public ResultJson updatePwd(HttpServletRequest request, @RequestParam Long id,@RequestParam String password) {
		verifyUserTokenMatch(request.getHeader(jwtCfg.getHeader()), id);

		return ResultJson.ok(authService.updatePassword(id,  password));
	}
    
	@PostMapping("/update")
    @ApiOperation(value = "修改用户信息")
	public ResultJson updateUser(HttpServletRequest request, @RequestBody User user) {
		verifyUserTokenMatch(request.getHeader(jwtCfg.getHeader()), user.getUserId());

		User old = authService.selectUserById(user.getUserId());		
		user.setPassword(old.getPassword());
		return ResultJson.ok(authService.updateUser(user)); 
	}
    
	@PostMapping("/update/rest")
    @ApiOperation(value = "选择性修改用户信息，无token用户验证")
	public ResultJson updateUserRest(@RequestBody User user) {
		User old = authService.selectUserById(user.getUserId());		
		user.setPassword(old.getPassword());
		int row = authService.updateUserSelective(user);
		return row>0?ResultJson.ok():ResultJson.failure(ResultCode.SERVER_ERROR); 
	}

    @PostMapping(value = "/adminResetPwd")
    @ApiOperation(value = "修改密码")
	public ResultJson adminResetPwd(HttpServletRequest request, @RequestBody User user) {
		return ResultJson.ok(authService.updatePassword(user.getUserId(), user.getPassword()));
    }
    
    
    private void verifyUserTokenMatch(String token, Long userId) {
    	UserDetail userDetail = authService.getUserByToken(token);
    	if(userDetail == null || userDetail.getId() != userId) {
        	throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "无法验证用户信息"));
    	}
    	
    }
}
