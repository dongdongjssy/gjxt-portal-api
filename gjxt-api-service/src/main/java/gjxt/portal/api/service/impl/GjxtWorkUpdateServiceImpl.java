package gjxt.portal.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.GjxtWorkUpdate;
import gjxt.portal.api.mapper.GjxtWorkUpdateMapper;
import gjxt.portal.api.service.GjxtWorkUpdateService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 工作动态 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Service
public class GjxtWorkUpdateServiceImpl extends ServiceImpl<GjxtWorkUpdateMapper, GjxtWorkUpdate> implements GjxtWorkUpdateService {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    @Override
    public IPage<GjxtWorkUpdate> getWorkUpdateList(Page page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<GjxtWorkUpdate> queryWrapper = new QueryWrapper<>();
        GjxtWorkUpdate gjxtWorkUpdate = JSON.parseObject(JSON.toJSONString(map), GjxtWorkUpdate.class);
        MybatisPlusUtil.notNullField(gjxtWorkUpdate, queryWrapper);
        return baseMapper.getWorkUpdateList(page, queryWrapper);
    }

    @Override
    public GjxtWorkUpdate getWorkUpdateById(Long id) {
        return baseMapper.getWorkUpdateById(id);
    }
}
