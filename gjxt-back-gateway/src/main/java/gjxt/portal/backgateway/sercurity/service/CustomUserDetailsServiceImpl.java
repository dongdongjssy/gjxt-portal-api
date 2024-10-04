package gjxt.portal.backgateway.sercurity.service;

import gjxt.portal.backgateway.sercurity.domain.auth.User;
import gjxt.portal.backgateway.sercurity.domain.auth.UserDetail;
import gjxt.portal.backgateway.sercurity.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/* * 登陆身份认证
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
    	User user = userRepo.findByUsername(name);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("用户不存在： '%s'.", name));
        }
        return new UserDetail(user);
    }
}
