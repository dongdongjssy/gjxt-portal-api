package gjxt.portal.sys.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import gjxt.portal.sys.entity.SysMenu;

import java.util.List;


/**
 * 菜单管理
 *
 * @author Mark sunlightcs@gmail.com
 */
public interface SysMenuService extends IService<SysMenu> {

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<SysMenu> queryListParentId(Long parentId, List<Long> menuIdList);

	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 */
	List<SysMenu> queryListParentId(Long parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenu> queryNotButtonList();
	
	/**
	 * 获取用户菜单列表
	 */
	List<SysMenu> getUserMenuList(Long userId);

	/**
	 * 删除
	 */
	void delete(Long menuId);

	IPage queryPage(SysMenu sysMenuEntity);
	
	/**
	 * 所有菜单树型列表
	 */
	List<SysMenu> getAllMenuList(List<Long> menuIdList);
}
