package gjxt.portal.apigateway.sercurity.domain.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @author : goose
 * createAt: 2019/4/1
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetail  implements UserDetails {

    private ClientUser user;
    private boolean expired;
    private Long expiration;
    public UserDetail() {}
    
    public UserDetail(ClientUser user) {
    	this.user = user;
    }

    public UserDetail(Role role) {
    	user = new ClientUser();
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);
        user.setRoles(roles);
    }

    public UserDetail(Long id,String phone,Set<Role> roles) {
        user = new ClientUser();
        user.setId(id);
        user.setRoles(roles);
        user.setPhone(phone);
    }

    //返回分配给用户的角色列表
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Iterator<Role> iter = user.getRoles().iterator();
        while(iter.hasNext()) {
        	String roleName = iter.next().getName();
        	authorities.add(new SimpleGrantedAuthority(roleName));
        }

        return authorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public long getId() {
        return user.getId();
    }



    /**
     * 账户是否未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     *  账户是否未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    /**
     * 密码是否未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /** 账户是否激活
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


    @JsonIgnore
    public Role getRole() {
        return user.getRoles().iterator().next();
    }

    public void setRole(Role role) {
        user.getRoles().clear();
        user.getRoles().add(role);
    }

//    public void setId(Long id) {
//        user.setId(id);
//    }

    public void setUsername(String username) {
        user.setUsername(username);
    }

    public void setPassword(String password) {
        user.setPassword(password);
    }


    public ClientUser getUser() {
        return user;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }
    
    
}
