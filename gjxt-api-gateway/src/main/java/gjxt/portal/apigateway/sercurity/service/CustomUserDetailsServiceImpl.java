package gjxt.portal.apigateway.sercurity.service;

import gjxt.portal.apigateway.sercurity.domain.auth.ClientUser;
import gjxt.portal.apigateway.sercurity.domain.auth.UserDetail;
import gjxt.portal.apigateway.sercurity.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * 登陆身份认证
 * @author: goose
 * createAt: 2019/4/1
 */
@Component(value="CustomUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepo;

    public CustomUserDetailsServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
    	ClientUser user = userRepo.findByPhone(name);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("用户不存在： '%s'.", name));
        }      
        
        return new UserDetail(user);
    }
}
