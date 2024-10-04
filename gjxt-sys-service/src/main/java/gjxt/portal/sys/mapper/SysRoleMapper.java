package gjxt.portal.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import gjxt.portal.sys.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createId);
	
	List<SysRole> queryRoleList(Long userId);

	List<Long> queryRoleIdListByNames(List<String> roleNames);
}
