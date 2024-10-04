package gjxt.portal.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.api.common.exception.BusinessException;
import gjxt.portal.sys.entity.SysUser;
import gjxt.portal.sys.mapper.SysUserMapper;
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
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;

	@Override
	public IPage<SysUser> queryPage(SysUser sysUser) {
		LambdaQueryWrapper<SysUser> query=new LambdaQueryWrapper<>();
		Page<SysUser> page = this.page(sysUser.getPage(), query);
		page.getRecords().forEach(u->{
			u.setRoleList(sysRoleService.queryRoleList(u.getUserId()));
		});
		return page;
	}

	@Override
	public List<String> queryAllPerms(Long userId) {
		return baseMapper.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	@Override
	public SysUser queryByUserName(String username) {
		return baseMapper.queryByUserName(username);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveUser(SysUser user) {
		//sha256加密
		this.save(user);
		//检查角色是否越权
		checkRole(user);
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList(), user.getCreateId());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysUser user) {
		this.updateById(user);
		//检查角色是否越权
		checkRole(user);
			//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList(), user.getModifyId());
	}

	@Override
	public void deleteBatch(Long[] userId) {
		this.removeByIds(Arrays.asList(userId));
	}

	@Override
	public boolean updatePassword(Long userId, String password, String newPassword) {
		SysUser userEntity = new SysUser();
		userEntity.setPassword(newPassword);
		return this.update(userEntity,
				new QueryWrapper<SysUser>().eq("user_id", userId).eq("password", password));
	}

	@Override
	public boolean checkLoginNameUnique(String userName) {
		LambdaQueryWrapper<SysUser> wrapper=new LambdaQueryWrapper<>();
		wrapper.eq(SysUser::getUsername,userName);
		SysUser sysUserEntity = baseMapper.selectOne(wrapper);
		return sysUserEntity==null;
	}
	/**
	 * 检查角色是否越权
	 */
	private void checkRole(SysUser user){
		if(user.getRoleIdList() == null || user.getRoleIdList().size() == 0){
			return;
		}
		//如果不是超级管理员，则需要判断用户的角色是否自己创建
		if(user.getCreateId() == Constant.SUPER_ADMIN){
			return ;
		}
		
		//查询用户创建的角色列表
		List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateId());

		//判断是否越权
		if(!roleIdList.containsAll(user.getRoleIdList())){
			throw new BusinessException("新增用户所选角色，不是本人创建");
		}
	}
}