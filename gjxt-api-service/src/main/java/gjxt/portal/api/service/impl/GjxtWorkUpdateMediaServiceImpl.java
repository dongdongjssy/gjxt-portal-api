package gjxt.portal.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.GjxtWorkUpdateMedia;
import gjxt.portal.api.mapper.GjxtWorkUpdateMediaMapper;
import gjxt.portal.api.service.GjxtWorkUpdateMediaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工作动态关联媒体 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Service
public class GjxtWorkUpdateMediaServiceImpl extends ServiceImpl<GjxtWorkUpdateMediaMapper, GjxtWorkUpdateMedia> implements GjxtWorkUpdateMediaService {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    @Override
    public IPage<GjxtWorkUpdateMedia> queryPage(Page page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<GjxtWorkUpdateMedia> queryWrapper = new QueryWrapper<>();
        GjxtWorkUpdateMedia gjxtWorkUpdateMedia = JSON.parseObject(JSON.toJSONString(map), GjxtWorkUpdateMedia.class);
        MybatisPlusUtil.notNullField(gjxtWorkUpdateMedia, queryWrapper);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<GjxtWorkUpdateMedia> selectWorkUpdateMediaByWorkUpdateId(Long id) {
        return baseMapper.selectWorkUpdateMediaByWorkUpdateId(id);
    }
}
