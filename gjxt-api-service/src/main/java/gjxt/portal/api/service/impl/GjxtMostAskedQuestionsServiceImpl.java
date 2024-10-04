package gjxt.portal.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.GjxtMostAskedQuestions;
import gjxt.portal.api.mapper.GjxtMostAskedQuestionsMapper;
import gjxt.portal.api.service.GjxtMostAskedQuestionsService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 常见问题 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Service
public class GjxtMostAskedQuestionsServiceImpl extends ServiceImpl<GjxtMostAskedQuestionsMapper, GjxtMostAskedQuestions> implements GjxtMostAskedQuestionsService {

    /**
     * 分页查询
     */
    @Override
    public IPage<GjxtMostAskedQuestions> queryPage(Page<GjxtMostAskedQuestions> page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<GjxtMostAskedQuestions> queryWrapper = new QueryWrapper<>();
        GjxtMostAskedQuestions gjxtMostAskedQuestions = JSON.parseObject(JSON.toJSONString(map), GjxtMostAskedQuestions.class);
        MybatisPlusUtil.notNullField(gjxtMostAskedQuestions, queryWrapper);
        return baseMapper.selectPage(page, queryWrapper);
    }
}
