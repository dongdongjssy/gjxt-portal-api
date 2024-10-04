package gjxt.portal.api.service.impl;

import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.GjxtSecondaryVocationalScholarship;
import gjxt.portal.api.mapper.GjxtSecondaryVocationalScholarshipMapper;
import gjxt.portal.api.service.GjxtSecondaryVocationalScholarshipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * <p>
 * 中职奖学金名单 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-05-15
 */
@Service
public class GjxtSecondaryVocationalScholarshipServiceImpl extends ServiceImpl<GjxtSecondaryVocationalScholarshipMapper, GjxtSecondaryVocationalScholarship> implements GjxtSecondaryVocationalScholarshipService {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    @Override
    public IPage<GjxtSecondaryVocationalScholarship> queryPage(Page page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<GjxtSecondaryVocationalScholarship> queryWrapper = new QueryWrapper<>();
        GjxtSecondaryVocationalScholarship gjxtSecondaryVocationalScholarship = JSONObject.parseObject(JSONObject.toJSONString(map), GjxtSecondaryVocationalScholarship.class);
        if (!StringUtils.isEmpty(gjxtSecondaryVocationalScholarship.getName())) {
            queryWrapper.like("name", gjxtSecondaryVocationalScholarship.getName());
        }
        if (!StringUtils.isEmpty(gjxtSecondaryVocationalScholarship.getSchoolName())) {
            queryWrapper.like("school_name", gjxtSecondaryVocationalScholarship.getSchoolName());
        }

        if (!StringUtils.isEmpty(gjxtSecondaryVocationalScholarship.getProvince())) {
            queryWrapper.like("province", gjxtSecondaryVocationalScholarship.getProvince());
        }

        MybatisPlusUtil.notNullField(gjxtSecondaryVocationalScholarship, queryWrapper);
        IPage<GjxtSecondaryVocationalScholarship> gjxtSecondaryVocationalScholarshipPage = baseMapper.selectPage(page, queryWrapper);
        return gjxtSecondaryVocationalScholarshipPage;
    }
}
