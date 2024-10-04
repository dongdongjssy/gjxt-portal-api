package gjxt.portal.api.service.impl;

import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.GjxtMultimedia;
import gjxt.portal.api.mapper.GjxtMultimediaMapper;
import gjxt.portal.api.service.GjxtMultimediaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * <p>
 * 多媒体文件 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Service
public class GjxtMultimediaServiceImpl extends ServiceImpl<GjxtMultimediaMapper, GjxtMultimedia> implements GjxtMultimediaService {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    @Override
    public IPage<GjxtMultimedia> queryPage(Page page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<GjxtMultimedia> queryWrapper = new QueryWrapper<>();
        GjxtMultimedia gjxtMultimedia = JSONObject.parseObject(JSONObject.toJSONString(map), GjxtMultimedia.class);
        MybatisPlusUtil.notNullField(gjxtMultimedia, queryWrapper);
        IPage<GjxtMultimedia> gjxtMultimediaPage = baseMapper.selectPage(page, queryWrapper);
        return gjxtMultimediaPage;
    }
}
