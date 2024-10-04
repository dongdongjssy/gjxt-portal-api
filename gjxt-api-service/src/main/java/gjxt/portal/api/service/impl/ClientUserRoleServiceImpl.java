package gjxt.portal.api.service.impl;

import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.ClientUserRole;
import gjxt.portal.api.mapper.ClientUserRoleMapper;
import gjxt.portal.api.service.ClientUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * <p>
 * 客户端用户角色关联 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-02-23
 */
@Service
public class ClientUserRoleServiceImpl extends ServiceImpl<ClientUserRoleMapper, ClientUserRole> implements ClientUserRoleService {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    @Override
    public IPage<ClientUserRole> queryPage(Page page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<ClientUserRole> queryWrapper = new QueryWrapper<>();
        ClientUserRole clientUserRole = JSONObject.parseObject(JSONObject.toJSONString(map), ClientUserRole.class);
        MybatisPlusUtil.notNullField(clientUserRole, queryWrapper);
        IPage<ClientUserRole> clientUserRolePage = baseMapper.selectPage(page, queryWrapper);
        return clientUserRolePage;
    }
}
