package gjxt.portal.backgateway.sercurity.domain.auth;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author : goose
 * createAt: 2019/4/1
 */
@Data
@Builder
@Entity
@Table(name="sys_role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
    private Integer roleId;
        
    @Size(max=11)
    private String roleName;
    
    @Tolerate
    public Role() {}

    

}
