package gjxt.portal.backgateway.sercurity.service;

import gjxt.portal.backgateway.sercurity.domain.ResultCode;
import gjxt.portal.backgateway.sercurity.domain.ResultJson;
import gjxt.portal.backgateway.sercurity.domain.auth.ResponseUserToken;
import gjxt.portal.backgateway.sercurity.domain.auth.Role;
import gjxt.portal.backgateway.sercurity.domain.auth.User;
import gjxt.portal.backgateway.sercurity.domain.auth.UserDetail;
import gjxt.portal.backgateway.sercurity.exception.CustomException;
import gjxt.portal.backgateway.sercurity.repo.RoleRepository;
import gjxt.portal.backgateway.sercurity.repo.UserRepository;
import gjxt.portal.backgateway.sercurity.utils.JwtConfig;
import gjxt.portal.backgateway.sercurity.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author: goose
 * createAt: 2019/4/1
 */
@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtTokenUtil;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    
    public static final int ROLE_USER_ID = 1;

    @Resource
    private JwtConfig jwtCfg;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           @Qualifier("CustomUserDetailsService") UserDetailsService userDetailsService,
                           JwtUtils jwtTokenUtil,
                           UserRepository userRepo,
                           RoleRepository roleRepo) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public UserDetail register(User user) {
        if(userRepo.findByUsername(user.getUsername())!=null) {
            throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "用户已存在"));
        }
        Set<Role> roles = new HashSet<Role>();
        if(user.getRoles()==null || user.getRoles().size()==0) {
        	Role role = roleRepo.findByRoleName(user.getRoleName());
        	 roles.add(role);
        }else {
            for(Role r:user.getRoles()) {
            	Role role = roleRepo.findByRoleName(r.getRoleName());
                if(role==null) {
                	throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "用户权限不存在"));
                }
                roles.add(role);
            }
        }

        user.setRoles(roles);
        encodePassword(user, user.getPassword());        
        user.setPasswordResetDate(new Date());
        userRepo.save(user);
        return new UserDetail(user);
    }

    @Override
    public ResponseUserToken login(String username, String password) {
        //用户验证
        final Authentication authentication = authenticate(username, password);
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        final UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        if(userDetail == null || userDetail.getUser() == null) {
        	throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR, "鉴权失败： 无法获取用户信息"));
        }

        Set<Role> roles = userDetail.getUser().getRoles();
        Iterator<Role> it = roles.iterator();
        while(it.hasNext()) {
        	Role next = it.next();
        	if(next.getRoleName().equals("商户")) {
        		throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR, "鉴权失败：您的账户无权限登陆"));
        	}
        }

        userDetail.setPassword("");
		ResponseUserToken res = jwtTokenUtil.generateTokens(userDetail);
		if(res != null) {
	        jwtTokenUtil.putToken(username, res.getToken(), res.getRefreshToken());
	        // 拼接前缀
	        res.setToken(jwtCfg.getPrefix()+res.getToken());
	        res.setRefreshToken(jwtCfg.getPrefix()+res.getRefreshToken());
		}
		return res;

    }

    @Override
    public void logout(String token) {
        token = token.replace(jwtCfg.getPrefix(), "");
        String userName = jwtTokenUtil.getUsernameFromToken(token);
        jwtTokenUtil.deleteToken(userName);
    }

    @Override
    public ResponseUserToken refresh(String accessToken, String refreshToken) {
        accessToken = accessToken.replace(jwtCfg.getPrefix(), "");
    	refreshToken = refreshToken.replace(jwtCfg.getPrefix(), "");    
		String username = jwtTokenUtil.validateBeforeRefresh(accessToken, refreshToken);
		if(StringUtils.isBlank(username)) {
			return null;
		}
		UserDetail userDetail = (UserDetail) userDetailsService.loadUserByUsername(username);		
		if(!jwtTokenUtil.canTokenBeRefreshed(refreshToken, userDetail.getUser().getPasswordResetDate()))
			return null;
		
		ResponseUserToken res = jwtTokenUtil.generateTokens(userDetail);
		if(res != null) {
	        jwtTokenUtil.putToken(username, res.getToken(), res.getRefreshToken());
		}
		return res;
    }

    @Override
    public UserDetail getUserByToken(String token) {
		if(StringUtils.isAllBlank(token))
    		throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "用户不存在"));
 
        token = token.replace(jwtCfg.getPrefix(), "");
        return jwtTokenUtil.getUserFromToken(token);
    }
    
	@Override
	public boolean validateToken(String token) {
		if(StringUtils.isAllBlank(token))
			return false;
		
        token = token.replace(jwtCfg.getPrefix(), "");
        String username = jwtTokenUtil.getUsernameFromToken(token);
        
		UserDetail userDetail = (UserDetail) userDetailsService.loadUserByUsername(username);		
		if(userDetail == null || !jwtTokenUtil.canTokenBeRefreshed(token, userDetail.getUser().getPasswordResetDate()))
			return false;
		
		return true;
	}

    private Authentication authenticate(String username, String password) {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR, e.getMessage()));
        }
    }

	@Override
	public User selectUserById(Long id) {
		Optional<User> user = userRepo.findById(id);
		if(!user.isPresent()) {
    		throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "用户不存在"));
		}
		return user.get();
	}
	
	@Override
	public User updateUser(User user) {
		return userRepo.save(user);		
	}
	
	@Override
	public User updatePassword(Long id, String password) {
		if(StringUtils.isAllBlank(password)) {
			throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "密码不能为空"));
		}
		User user = selectUserById(id);
        if(user.getUsername().equals("admin")){
            throw new CustomException(ResultJson.failure(ResultCode.ADMIN_USER));
        }
		encodePassword(user, password);
        user.setPasswordResetDate(new Date());
		return userRepo.save(user);
	}


	private void encodePassword(User user, String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(password));  
	}

	@Override
	public int updateUserSelective(User user) {
		return userRepo.updateUserSelective(user);
	}
//	@Override
//	public User updateByPhone(User user) {
//    	User old = userRepo.findByPhone(user.getPhonenumber());
//        if(old == null) {
//            throw new CustomException(ResultJson.failure(ResultCode.USER_NOT_EXISTS, ResultCode.USER_NOT_EXISTS.getMsg()));            
//        }
//        try {
//        	userRepo.updateByPhone(user);
//        } catch(Exception e) {
//        	throw new CustomException(ResultJson.failure(ResultCode.SERVER_ERROR, "User modify fails:"+e.getMessage()));
//        }
//        user.setPassword("");
//        return user;
//    }
//	
}
