package gjxt.portal.api.service.impl;

import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.GjxtPolicyExplanation;
import gjxt.portal.api.mapper.GjxtPolicyExplanationMapper;
import gjxt.portal.api.service.GjxtPolicyExplanationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * <p>
 * 政策解读 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Service
public class GjxtPolicyExplanationServiceImpl extends ServiceImpl<GjxtPolicyExplanationMapper, GjxtPolicyExplanation> implements GjxtPolicyExplanationService {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    @Override
    public IPage<GjxtPolicyExplanation> queryPage(Page page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<GjxtPolicyExplanation> queryWrapper = new QueryWrapper<>();
        GjxtPolicyExplanation gjxtPolicyExplanation = JSONObject.parseObject(JSONObject.toJSONString(map), GjxtPolicyExplanation.class);
        MybatisPlusUtil.notNullField(gjxtPolicyExplanation, queryWrapper);
        IPage<GjxtPolicyExplanation> gjxtPolicyExplanationPage = baseMapper.selectPage(page, queryWrapper);
        return gjxtPolicyExplanationPage;
    }
}
