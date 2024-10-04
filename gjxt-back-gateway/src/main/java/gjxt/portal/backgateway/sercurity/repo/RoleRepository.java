package gjxt.portal.backgateway.sercurity.repo;

import gjxt.portal.backgateway.sercurity.domain.auth.Role;
import org.springframework.data.repository.CrudRepository;



public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	public void deleteByRoleName(String name);
	
	public Role findByRoleName(String name);

}
