package gjxt.portal.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.sys.service.SysMenuService;
import gjxt.portal.sys.entity.SysMenu;
import gjxt.portal.sys.mapper.SysMenuMapper;
import gjxt.portal.sys.service.SysRoleMenuService;
import gjxt.portal.sys.service.SysUserService;
import gjxt.portal.sys.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	@Override
	public IPage queryPage(SysMenu sysMenuEntity) {

		LambdaQueryWrapper<SysMenu> query=new LambdaQueryWrapper<>();
		if(sysMenuEntity.getType()!=null){
			query.eq(SysMenu::getType,sysMenuEntity.getType());
		}

		if(sysMenuEntity.getParentId()!=null){
			query.eq(SysMenu::getParentId,sysMenuEntity.getParentId());
		}

		Page<SysMenu> page = this.page(new Page<>(), query);
		return page;
	}

	
	@Override
	public List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList) {
		List<SysMenu> menuList = queryListParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		
		List<SysMenu> userMenuList = new ArrayList<>();
		for(SysMenu menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenu> queryListParentId(Long parentId) {
		return baseMapper.queryListParentId(parentId);
	}

	@Override
	public List<SysMenu> queryNotButtonList() {
		return baseMapper.queryNotButtonList();
	}

	@Override
	public List<SysMenu> getUserMenuList(Long userId) {

//		//系统管理员，拥有最高权限
//		if(userId == Constant.SUPER_ADMIN){
//			return getMenuList(null);
//		}

		if(userId==null){
			return getMenuList(null);
		}
		//用户菜单列表
		List<Long> menuIdList = sysUserService.queryAllMenuId(userId);
		return getMenuList(menuIdList);
	}

	/**
	 * 获取拥有的菜单列表
	 * @param menuIdList
	 * @return
	 */
	private List<SysMenu> getMenuList(List<Long> menuIdList) {
		// 查询拥有的所有菜单
		List<SysMenu> menus = this.baseMapper.selectList(new QueryWrapper<SysMenu>()
				.in(Objects.nonNull(menuIdList), "menu_id", menuIdList).in("type", 0, 1).orderByAsc("order_num"));
		// 将id和菜单绑定
		HashMap<Long, SysMenu> menuMap = new HashMap<>(12);
		for (SysMenu s : menus) {
			menuMap.put(s.getMenuId(), s);
		}
		// 使用迭代器,组装菜单的层级关系
		Iterator<SysMenu> iterator = menus.iterator();
		while (iterator.hasNext()) {
			SysMenu menu = iterator.next();
			SysMenu parent = menuMap.get(menu.getParentId());
			if (Objects.nonNull(parent)) {
				parent.getChildren().add(menu);
				// 将这个菜单从当前节点移除
				iterator.remove();
			}
		}

		return menus;
	}

	@Override
	public void delete(Long menuId){
		//删除菜单
		this.removeById(menuId);
		//删除菜单与角色关联
		Map map=new HashMap();
		map.put("menu_id", menuId);
		sysRoleMenuService.removeByMap(map);
	}

	/**
	 * 获取所有菜单列表
	 */
	@Override
	public List<SysMenu> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<SysMenu> menuList = queryListParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		
		return menuList;
	}

	/**
	 * 递归
	 */
	private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIdList){
		List<SysMenu> subMenuList = new ArrayList<SysMenu>();
		
		for(SysMenu entity : menuList){
			//目录
			if(entity.getType() == Constant.MenuType.CATALOG.getValue()){
				List<SysMenu> sysMenuEntities = queryListParentId(entity.getMenuId(), menuIdList);
				entity.setChildren(getMenuTreeList(sysMenuEntities, menuIdList));
			}
			subMenuList.add(entity);
		}
		
		return subMenuList;
	}
}
