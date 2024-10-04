package gjxt.portal.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.GjxtPolicyFileMedia;
import gjxt.portal.api.mapper.GjxtPolicyFileMediaMapper;
import gjxt.portal.api.service.GjxtPolicyFileMediaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 政策文件关联媒体 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Service
public class GjxtPolicyFileMediaServiceImpl extends ServiceImpl<GjxtPolicyFileMediaMapper, GjxtPolicyFileMedia> implements GjxtPolicyFileMediaService {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    @Override
    public IPage<GjxtPolicyFileMedia> queryPage(Page page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<GjxtPolicyFileMedia> queryWrapper = new QueryWrapper<>();
        GjxtPolicyFileMedia gjxtPolicyFileMedia = JSON.parseObject(JSON.toJSONString(map), GjxtPolicyFileMedia.class);
        MybatisPlusUtil.notNullField(gjxtPolicyFileMedia, queryWrapper);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<GjxtPolicyFileMedia> selectPolicyFileMediaByPolicyFileId(Long id) {
        return baseMapper.selectPolicyFileMediaByPolicyFileId(id);
    }
}
