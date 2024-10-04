package gjxt.portal.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.GjxtHighScholarship;
import gjxt.portal.api.entity.GjxtPostgraduateScholarship;
import gjxt.portal.api.mapper.GjxtHighScholarshipMapper;
import gjxt.portal.api.mapper.GjxtPostgraduateScholarshipMapper;
import gjxt.portal.api.service.GjxtHighScholarshipService;
import gjxt.portal.api.service.GjxtPostgraduateScholarshipService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 研究生奖学金名单 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-05-15
 */
@Service
public class GjxtPostgraduateScholarshipServiceImpl extends ServiceImpl<GjxtPostgraduateScholarshipMapper, GjxtPostgraduateScholarship> implements GjxtPostgraduateScholarshipService {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    @Override
    public IPage<GjxtPostgraduateScholarship> queryPage(Page page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<GjxtPostgraduateScholarship> queryWrapper = new QueryWrapper<>();
        GjxtPostgraduateScholarship gjxtHighScholarship = JSONObject.parseObject(JSONObject.toJSONString(map), GjxtPostgraduateScholarship.class);
        MybatisPlusUtil.notNullField(gjxtHighScholarship, queryWrapper);
        if (!StringUtils.isEmpty(gjxtHighScholarship.getName())) {
            queryWrapper.like("name", gjxtHighScholarship.getName());
        }
        if (!StringUtils.isEmpty(gjxtHighScholarship.getSchoolName())) {
            queryWrapper.like("school_name", gjxtHighScholarship.getSchoolName());
        }

        if (!StringUtils.isEmpty(gjxtHighScholarship.getProvince())) {
            queryWrapper.like("province", gjxtHighScholarship.getProvince());
        }
        IPage<GjxtPostgraduateScholarship> gjxtHighScholarshipPage = baseMapper.selectPage(page, queryWrapper);
        return gjxtHighScholarshipPage;
    }
}
