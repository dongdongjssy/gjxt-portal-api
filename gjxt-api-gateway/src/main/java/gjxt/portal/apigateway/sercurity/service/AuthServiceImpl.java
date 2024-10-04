package gjxt.portal.apigateway.sercurity.service;

import gjxt.portal.apigateway.sercurity.domain.ResultCode;
import gjxt.portal.apigateway.sercurity.domain.ResultJson;
import gjxt.portal.apigateway.sercurity.domain.auth.ClientUser;
import gjxt.portal.apigateway.sercurity.domain.auth.ResponseUserToken;
import gjxt.portal.apigateway.sercurity.domain.auth.Role;
import gjxt.portal.apigateway.sercurity.domain.auth.UserDetail;
import gjxt.portal.apigateway.sercurity.exception.CustomException;
import gjxt.portal.apigateway.sercurity.redis.RedisUtil;
import gjxt.portal.apigateway.sercurity.repo.RoleRepository;
import gjxt.portal.apigateway.sercurity.repo.UserRepository;
import gjxt.portal.apigateway.sercurity.utils.JwtConfig;
import gjxt.portal.apigateway.sercurity.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


/**
 * @author: goose
 * createAt: 2019/4/1
 */
@Service
public class AuthServiceImpl implements AuthService {
    private static final int VisitorStaff = 2;
    private static final int TemporaryStaff = 3;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtTokenUtil;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    private static Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    public static final int ROLE_USER_ID = 1;
    public static final String STATUS_ACTIVE = "0";
    public static final int IS_DELETE_NO = 0;
    public static final String CODE_SUCCESS = "0";

    @Autowired
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


    @Autowired
    private RedisUtil redisUtil;

    @Override
    public UserDetail register(ClientUser user) {
        // todo 判断验证码
        Set<Role> roles = new HashSet<>();
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Role role = roleRepo.findByName(user.getRoleName());
            roles.add(role);
        } else {
            for (Role r : user.getRoles()) {
                Role role = roleRepo.findByName(r.getName());
                if (role == null) {
                    throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "用户权限不存在"));
                }
                roles.add(role);
            }
        }

        if (userRepo.findByPhone(user.getPhone()) != null || userRepo.findByIdNumber(user.getIdNumber()) != null) {
            throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "用户已存在（手机号和身份证号不能重复注册）"));
        }
        user.setRoles(roles);
        user.setUsername(user.getPhone());
        encodePassword(user, user.getPassword());
        user.setStatus(0);
        userRepo.save(user);
        return new UserDetail(user);
    }

    private void encodePassword(ClientUser user, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
    }


    @Override
    public ResponseUserToken login(String phone, String password) {
        //用户验证
        final Authentication authentication = authenticate(phone, password);
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        final UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        if (userDetail == null || userDetail.getUser() == null) {
            throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR, "鉴权失败： 无法获取用户信息"));
        }

        if (null != userDetail.getUser().getStatus() && userDetail.getUser().getStatus() != 0) {
            throw new CustomException(ResultJson.failure(ResultCode.USER_NOT_EXISTS));
        }

        //查询redis是否存在token
        String tokenValue = redisUtil.getValueByKey(phone);
        if (null != tokenValue) {
            //  检查token是否过期
            UserDetail userFromToken = jwtTokenUtil.getUserFromToken(tokenValue);
            if (userFromToken != null) {
                ResponseUserToken res = jwtTokenUtil.generateTokens(userDetail);
                res.setToken(jwtCfg.getPrefix() + tokenValue);
                return res;

            }
        }
        ResponseUserToken res = jwtTokenUtil.generateTokens(userDetail);
        // 重新生成token
        if (res != null) {
            jwtTokenUtil.putToken(phone, res.getToken(), res.getRefreshToken());
            // 拼接前缀
            res.setToken(jwtCfg.getPrefix() + res.getToken());
            res.setRefreshToken(jwtCfg.getPrefix() + res.getRefreshToken());
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
        if (StringUtils.isBlank(username)) {
            return null;
        }
        UserDetail userDetail = (UserDetail) userDetailsService.loadUserByUsername(username);
//		if(!jwtTokenUtil.canTokenBeRefreshed(refreshToken, userDetail.getUser().getPasswordResetDate()))
//			return null;

        ResponseUserToken res = jwtTokenUtil.generateTokens(userDetail);
        if (res != null) {
            jwtTokenUtil.putToken(username, res.getToken(), res.getRefreshToken());
        }
        return res;
    }

    @Override
    public UserDetail getUserByToken(String token) {
        if (StringUtils.isAllBlank(token))
            throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "用户不存在"));

        token = token.replace(jwtCfg.getPrefix(), "");
        return jwtTokenUtil.getUserFromToken(token);
    }

    @Override
    public boolean validateToken(String token) {
        if (StringUtils.isAllBlank(token))
            return false;

        token = token.replace(jwtCfg.getPrefix(), "");
        String username = jwtTokenUtil.getUsernameFromToken(token);

        UserDetail userDetail = (UserDetail) userDetailsService.loadUserByUsername(username);
//		if(userDetail == null || !jwtTokenUtil.canTokenBeRefreshed(token, userDetail.getUser().getPasswordResetDate()))
//			return false;

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
    public ClientUser selectUserById(Long id) {
        Optional<ClientUser> user = userRepo.findById(id);
        if (!user.isPresent()) {
            throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "用户不存在"));
        }
        return user.get();
    }

    @Override
    public ClientUser selectUserByPhone(String phone) {
        ClientUser user = userRepo.findByPhone(phone);
        return user;
    }

    @Override
    public ClientUser updateUser(ClientUser user) {
        return userRepo.save(user);
    }

    @Override
    public ClientUser updatePassword(Long id, String password) {
        if (StringUtils.isAllBlank(password)) {
            throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "密码不能为空"));
        }
        ClientUser user = selectUserById(id);
//		encodePassword(user, password);
        return userRepo.save(user);
    }

//	@Override
//	public ClientUser resetPwdByPhoneSmsCode(ClientUser user) {
//		if(StringUtils.isAllBlank(user.getPassword())) {
//			throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "密码不能为空"));
//		}
//		ClientUser clientUser = userRepo.findByPhone(user.getPhone());
//
//		if(clientUser == null) {
//			throw new CustomException(ResultJson.failure(ResultCode.USER_NOT_EXISTS, ResultCode.USER_NOT_EXISTS.getMsg()));
//		}
//		encodePassword(clientUser, user.getPassword());
//		return userRepo.save(clientUser);
//	}
//
//	private void encodePassword(ClientUser user, String password) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		user.setPassword(encoder.encode(password));
//	}

    @Override
    public int updateUserSelective(ClientUser user) {
        return userRepo.updateUserSelective(user);
    }

}
