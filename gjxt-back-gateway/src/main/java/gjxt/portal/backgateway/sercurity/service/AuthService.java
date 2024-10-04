package gjxt.portal.backgateway.sercurity.service;

import gjxt.portal.backgateway.sercurity.domain.auth.ResponseUserToken;
import gjxt.portal.backgateway.sercurity.domain.auth.User;
import gjxt.portal.backgateway.sercurity.domain.auth.UserDetail;

/**
 * @author: goose
 * createAt: 2019/4/1
 */
public interface AuthService {
    /**
     * 注册用户
     * @param user
     * @return
     */
    UserDetail register(User user);

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    ResponseUserToken login(String username, String password);

    /**
     * 登出
     * @param token
     */
    void logout(String token);

    /**
     * 刷新Token
     * @param accessToken
     * @return
     */
    ResponseUserToken refresh(String accessToken, String refreshToken);

    /**
     * 根据Token获取用户信息
     * @param token
     * @return
     */
    UserDetail getUserByToken(String token);
    
    boolean validateToken(String token);
    
    User selectUserById(Long id);
	
//	User selectUserByPhone(String phone);
	
	User updateUser(User user);
	
	User updatePassword(Long id, String password) ;
	
    /**
     * 选择性更新用户字段
     * @param user
     * @return
     */
	int updateUserSelective(User user);
	

}
