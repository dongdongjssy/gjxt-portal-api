package gjxt.portal.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.GjxtAnnouncementMedia;
import gjxt.portal.api.mapper.GjxtAnnouncementMediaMapper;
import gjxt.portal.api.service.GjxtAnnouncementMediaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知公告关联媒体 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Service
public class GjxtAnnouncementMediaServiceImpl extends ServiceImpl<GjxtAnnouncementMediaMapper, GjxtAnnouncementMedia> implements GjxtAnnouncementMediaService {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    @Override
    public IPage<GjxtAnnouncementMedia> queryPage(Page page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<GjxtAnnouncementMedia> queryWrapper = new QueryWrapper<>();
        GjxtAnnouncementMedia gjxtAnnouncementMedia = JSON.parseObject(JSON.toJSONString(map), GjxtAnnouncementMedia.class);
        MybatisPlusUtil.notNullField(gjxtAnnouncementMedia, queryWrapper);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<GjxtAnnouncementMedia> selectAnnouncementMediaByAnnouncementId(Long id) {
        return baseMapper.selectAnnouncementMediaByAnnouncementId(id);
    }
}
