package gjxt.portal.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.api.common.base.ResultCode;
import gjxt.portal.api.common.exception.BusinessException;
import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.GjxtCarousel;
import gjxt.portal.api.mapper.GjxtCarouselMapper;
import gjxt.portal.api.service.GjxtCarouselService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * <p>
 * 轮播图 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Service
public class GjxtCarouselServiceImpl extends ServiceImpl<GjxtCarouselMapper, GjxtCarousel> implements GjxtCarouselService {

    /**
     * 分页查询
     */
    @Override
    public IPage<GjxtCarousel> queryPage(Page page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<GjxtCarousel> queryWrapper = new QueryWrapper<>();
        GjxtCarousel gjxtCarousel = JSON.parseObject(JSON.toJSONString(map), GjxtCarousel.class);
        MybatisPlusUtil.notNullField(gjxtCarousel, queryWrapper);
        return baseMapper.selectPage(page, queryWrapper);
    }

    /**
     * 检查新添加或更新的轮播图顺序是否正确，如果和已存在的顺序冲突，抛出异常
     */
    @Override
    public void checkOrder(GjxtCarousel carousel) {
        QueryWrapper<GjxtCarousel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 0);
        queryWrapper.eq("carousel_order", carousel.getCarouselOrder());
        List<GjxtCarousel> result = baseMapper.selectList(queryWrapper);

        if (!result.isEmpty() && !Objects.isNull(carousel.getId()) && result.get(0).getId().equals(carousel.getId())) {
            return;
        }

        if (!result.isEmpty()) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "轮播图顺序已经存在，请重新指定。");
        }
    }
}
