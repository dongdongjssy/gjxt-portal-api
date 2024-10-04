package gjxt.portal.apigateway.sercurity.repo;

import gjxt.portal.apigateway.sercurity.domain.auth.Role;
import org.springframework.data.repository.CrudRepository;


public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	public void deleteByName(String name);
	
	public Role findByName(String name);

}
