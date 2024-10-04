package gjxt.portal.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.api.common.exception.BusinessException;
import gjxt.portal.sys.entity.SysRole;
import gjxt.portal.sys.entity.SysUserRole;
import gjxt.portal.sys.mapper.SysRoleMapper;
import gjxt.portal.sys.service.SysRoleMenuService;
import gjxt.portal.sys.service.SysRoleService;
import gjxt.portal.sys.service.SysUserRoleService;
import gjxt.portal.sys.service.SysUserService;
import gjxt.portal.sys.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 角色
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    
	@Override
	public IPage<SysRole> queryPage(SysRole sysRoleEntity) {

		LambdaQueryWrapper<SysRole> wrapper=new LambdaQueryWrapper<>();
		if(sysRoleEntity.getRoleName()!=null){
			wrapper.like(SysRole::getRoleName,sysRoleEntity.getRoleName());
		}
		IPage<SysRole> page = baseMapper.selectPage(sysRoleEntity.getPage(), wrapper);

		return page;
	}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(SysRole role) {
        this.save(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList(), role.getCreateId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRole role) {
        this.updateById(role);

        //检查权限是否越权
        checkPrems(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList(), role.getModifyId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除角色
        this.removeByIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        sysRoleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        sysUserRoleService.deleteBatch(roleIds);
    }

    @Override
	public List<Long> queryRoleIdList(Long createId) {
		return baseMapper.queryRoleIdList(createId);
	}
	
	@Override
	public boolean updateUserNumber(Long roleId, Long userId) {
		// 更新人员数量
    	int number = sysUserRoleService.count(new QueryWrapper<SysUserRole>()
				.eq("role_id", roleId)
				.ne("user_id", userId));
    	
    	SysRole role = new SysRole();
    	role.setUserNumber(number);
    	role.setRoleId(roleId);
    	return this.updateById(role);
	}
	
	/**
	 * 检查权限是否越权
	 */
	private void checkPrems(SysRole role){
		//如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
		if(role.getRoleId() == Constant.SUPER_ADMIN){
			return ;
		}
		
		//查询用户所拥有的菜单列表
		List<Long> menuIdList = sysUserService.queryAllMenuId(role.getRoleId());
		
		//判断是否越权
		if(!menuIdList.containsAll(role.getMenuIdList())){
			throw new BusinessException("新增角色的权限，已超出你的权限范围");
		}
	}

	@Override
	public List<SysRole> queryRoleList(Long userId) {
		return sysRoleMapper.queryRoleList(userId);
	}

	@Override
	public List<Long> queryRoleIdListByNames(String roleNames) {
		return sysRoleMapper.queryRoleIdListByNames(Arrays.asList(roleNames.split(Constants.COMMA)));
	}

}
