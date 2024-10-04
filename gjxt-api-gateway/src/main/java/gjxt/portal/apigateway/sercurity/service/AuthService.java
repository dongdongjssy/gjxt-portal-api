package gjxt.portal.apigateway.sercurity.service;


import gjxt.portal.apigateway.sercurity.domain.auth.ClientUser;
import gjxt.portal.apigateway.sercurity.domain.auth.ResponseUserToken;
import gjxt.portal.apigateway.sercurity.domain.auth.UserDetail;

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
    UserDetail register(ClientUser user);


	/**
	 * 登陆
	 * @param
	 * @return
	 */
	ResponseUserToken login(String phone,String password);

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
    
    ClientUser selectUserById(Long id);
	
	ClientUser selectUserByPhone(String phone);
	
	ClientUser updateUser(ClientUser user);
	
	ClientUser updatePassword(Long id, String password) ;
	
    /**
     * 选择性更新用户字段
     * @param user
     * @return
     */
	int updateUserSelective(ClientUser user);

}
