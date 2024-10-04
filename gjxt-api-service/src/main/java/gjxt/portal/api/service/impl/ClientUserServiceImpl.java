package gjxt.portal.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.ClientUser;
import gjxt.portal.api.mapper.ClientUserMapper;
import gjxt.portal.api.service.ClientUserService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 客户端用户 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-02-23
 */
@Service
public class ClientUserServiceImpl extends ServiceImpl<ClientUserMapper, ClientUser> implements ClientUserService {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    @Override
    public IPage<ClientUser> selectClientUsers(Page page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<ClientUser> queryWrapper = new QueryWrapper<>();
        ClientUser clientUser = JSON.parseObject(JSON.toJSONString(map), ClientUser.class);
        MybatisPlusUtil.notNullField(clientUser, queryWrapper);
        return baseMapper.selectClientUsers(page, queryWrapper);
    }

    @Override
    public ClientUser getClientUserById(Long id) {
        return baseMapper.getClientUserById(id);
    }
}
