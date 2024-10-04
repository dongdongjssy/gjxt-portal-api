package gjxt.portal.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.sys.entity.SysRoleMenu;
import gjxt.portal.sys.mapper.SysRoleMenuMapper;
import gjxt.portal.sys.service.SysRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



/**
 * 角色与菜单对应关系
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveOrUpdate(Long roleId, List<Long> menuIdList, Long userId) {
		//先删除角色与菜单关系
		deleteBatch(new Long[]{roleId});

		if(menuIdList == null || menuIdList.size() == 0){
			return ;
		}

		//保存角色与菜单关系
		for(Long menuId : menuIdList){
			SysRoleMenu sysRoleMenuEntity = new SysRoleMenu();
			sysRoleMenuEntity.setMenuId(menuId);
			sysRoleMenuEntity.setRoleId(roleId);
			sysRoleMenuEntity.setCreateId(userId);
			this.save(sysRoleMenuEntity);
		}
	}

	@Override
	public List<Long> queryMenuIdList(Long roleId) {
		return baseMapper.queryMenuIdList(roleId);
	}

	@Override
	public int deleteBatch(Long[] roleIds){
		return baseMapper.deleteBatch(roleIds);
	}

	@Override
	public IPage queryPage(Page page, SysRoleMenu sysRoleMenu) {
		LambdaQueryWrapper<SysRoleMenu> query=new LambdaQueryWrapper<>();
		if(sysRoleMenu.getRoleId()!=null){
			query.eq(SysRoleMenu::getRoleId,sysRoleMenu.getRoleId());
		}
		Page<SysRoleMenu> p = this.page(page, query);
		return p;
	}

}
