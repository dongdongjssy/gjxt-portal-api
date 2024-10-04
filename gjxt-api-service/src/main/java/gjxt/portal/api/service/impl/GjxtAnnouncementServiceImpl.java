package gjxt.portal.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.GjxtAnnouncement;
import gjxt.portal.api.mapper.GjxtAnnouncementMapper;
import gjxt.portal.api.service.GjxtAnnouncementService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 通知公告 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Service
public class GjxtAnnouncementServiceImpl extends ServiceImpl<GjxtAnnouncementMapper, GjxtAnnouncement> implements GjxtAnnouncementService {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    @Override
    public IPage<GjxtAnnouncement> getAnnouncementList(Page page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<GjxtAnnouncement> queryWrapper = new QueryWrapper<>();
        GjxtAnnouncement gjxtAnnouncement = JSON.parseObject(JSON.toJSONString(map), GjxtAnnouncement.class);
        MybatisPlusUtil.notNullField(gjxtAnnouncement, queryWrapper);
        return baseMapper.getAnnouncementList(page, queryWrapper);
    }

    @Override
    public GjxtAnnouncement getAnnouncementById(Long id) {
        return baseMapper.getAnnouncementById(id);
    }
}
