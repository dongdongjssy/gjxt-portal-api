package gjxt.portal.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import gjxt.portal.sys.entity.SysRole;

import java.util.List;


/**
 * 角色
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysRoleService extends IService<SysRole> {

	IPage<SysRole> queryPage(SysRole sysRoleEntity);

	void saveRole(SysRole role);

	void update(SysRole role);

	void deleteBatch(Long[] roleIds);
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createId);
	
	boolean updateUserNumber(Long roleId, Long userId);
	
	List<SysRole> queryRoleList(Long userId);
	
	List<Long> queryRoleIdListByNames(String roleNames);
}
