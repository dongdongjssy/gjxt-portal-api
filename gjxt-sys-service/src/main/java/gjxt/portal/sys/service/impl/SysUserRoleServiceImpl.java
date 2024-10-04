package gjxt.portal.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.sys.entity.SysUserRole;
import gjxt.portal.sys.mapper.SysUserRoleMapper;
import gjxt.portal.sys.service.SysRoleService;
import gjxt.portal.sys.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



/**
 * 用户与角色对应关系
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

	@Autowired
	private SysRoleService sysRoleService;

	@Override
	public int deleteByUserId(Long userId) {
		LambdaQueryWrapper<SysUserRole> wrapper=new LambdaQueryWrapper<>();
		wrapper.eq(SysUserRole::getUserId,userId);
		return baseMapper.delete(wrapper);
	}

	@Override
	public void saveOrUpdate(Long userId, List<Long> roleIdList, Long createId) {
		if(roleIdList == null || roleIdList.size() == 0){
			return ;
		}
		// 现有的用户角色ID
		List<Long> oldRoleIds = this.queryRoleIdList(userId);
		//保存用户与角色关系
		for(Long roleId : roleIdList){
			// 原来有，现在有，不操作
			if(!oldRoleIds.contains(roleId)) {
				// 原来没有，现在有，新增
				SysUserRole sysUserRoleEntity = new SysUserRole();
				sysUserRoleEntity.setUserId(userId);
				sysUserRoleEntity.setRoleId(roleId);
				sysUserRoleEntity.setCreateId(createId);
				this.save(sysUserRoleEntity);
				// 更新人员数量
				sysRoleService.updateUserNumber(roleId,userId);
			}
		}
		
		oldRoleIds.forEach(oldId->{
			if(!roleIdList.contains(oldId)) {
				// 原来有，现在没有，删除
				this.removeById(oldId);
				// 更新人员数量
				sysRoleService.updateUserNumber(oldId,userId);
			}
		});
    	
	}

	@Override
	public List<Long> queryRoleIdList(Long userId) {
		return baseMapper.queryRoleIdList(userId);
	}

	@Override
	public int deleteBatch(Long[] roleIds){
		return baseMapper.deleteBatch(roleIds);
	}

	@Override
	public IPage queryPage(Page page, SysUserRole sysUserRole) {
		LambdaQueryWrapper<SysUserRole> query=new LambdaQueryWrapper<>();
		if(sysUserRole.getRoleId()!=null){
			query.eq(SysUserRole::getRoleId,sysUserRole.getRoleId());
		}

		Page<SysUserRole> p = this.page(page, query);
		return p;
	}
}
