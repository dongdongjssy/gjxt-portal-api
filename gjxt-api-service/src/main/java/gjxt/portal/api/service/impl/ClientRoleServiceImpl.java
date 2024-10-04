package gjxt.portal.api.service.impl;

import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.ClientRole;
import gjxt.portal.api.mapper.ClientRoleMapper;
import gjxt.portal.api.service.ClientRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * <p>
 * 客户端角色 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-02-23
 */
@Service
public class ClientRoleServiceImpl extends ServiceImpl<ClientRoleMapper, ClientRole> implements ClientRoleService {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    @Override
    public IPage<ClientRole> queryPage(Page page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<ClientRole> queryWrapper = new QueryWrapper<>();
        ClientRole clientRole = JSONObject.parseObject(JSONObject.toJSONString(map), ClientRole.class);
        MybatisPlusUtil.notNullField(clientRole, queryWrapper);
        IPage<ClientRole> clientRolePage = baseMapper.selectPage(page, queryWrapper);
        return clientRolePage;
    }
}
