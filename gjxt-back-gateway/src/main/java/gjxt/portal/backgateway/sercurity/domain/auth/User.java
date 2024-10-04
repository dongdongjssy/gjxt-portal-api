package gjxt.portal.backgateway.sercurity.domain.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author : goose
 * createAt: 2019/4/1
 */
@Builder
@Data
@Entity
@DynamicUpdate
@Table(name = "sys_user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty(value = "帐号状态", required = false)
    private Integer status;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role", 
    joinColumns =@JoinColumn(name = "user_id", referencedColumnName = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"))
    private Set<Role> roles;

    @Transient
    private String roleName;
    @Tolerate
    public User() {}

    private Date passwordResetDate;
	
}
