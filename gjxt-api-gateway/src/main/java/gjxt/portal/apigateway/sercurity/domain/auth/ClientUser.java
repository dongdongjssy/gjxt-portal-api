package gjxt.portal.apigateway.sercurity.domain.auth;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.Set;


/**
 * @author : goose
 * createAt: 2019/4/1
 */
@Builder
@Data
@Entity
@DynamicUpdate
@Table(name = "client_user")
public class ClientUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "status")
    private Integer status;

    @Column(name = "id_number")
    private String idNumber;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @Transient
    private String roleName;

    @Transient
    private String smsCode;

    @Tolerate
    public ClientUser() {
    }

}
