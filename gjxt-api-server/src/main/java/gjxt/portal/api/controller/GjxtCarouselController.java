package gjxt.portal.api.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import gjxt.portal.api.common.base.R;
import gjxt.portal.api.common.base.ResultCode;
import gjxt.portal.api.common.exception.BusinessException;
import gjxt.portal.api.entity.GjxtCarousel;
import gjxt.portal.api.service.GjxtCarouselService;
import gjxt.portal.api.validate.Add;
import gjxt.portal.api.validate.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 轮播图 前端控制器
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */

@RestController
@RequestMapping("/api/carousel")
@RequiredArgsConstructor
public class GjxtCarouselController extends BaseController {
    private final GjxtCarouselService gjxtCarouselService;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @PostMapping("/list")
    public R listPage(@RequestBody Map<String, Object> params) {
        IPage<GjxtCarousel> data = gjxtCarouselService.queryPage(getPage(params), params);
        return R.ok(data.getRecords()).put("total", data.getTotal());
    }

    /**
     * 信息
     */
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable("id") Long id) {
        GjxtCarousel data = gjxtCarouselService.getById(id);
        if (Objects.isNull(data)) {
            throw new BusinessException(ResultCode.NOT_FOUND);
        }
        return R.ok(data);
    }

    /**
     * 保存
     */
    @PostMapping("/add")
    public R save(@Validated(value = Add.class) @RequestBody GjxtCarousel gjxtCarousel) {
        gjxtCarouselService.checkOrder(gjxtCarousel);
        setModifyData(gjxtCarousel);
        return toR(gjxtCarouselService.save(gjxtCarousel));
    }

    /**
     * 修改
     */
    @PostMapping("/edit")
    public R update(@Validated(value = Update.class) @RequestBody GjxtCarousel gjxtCarousel) {
        gjxtCarouselService.checkOrder(gjxtCarousel);
        return toR(gjxtCarouselService.updateById(gjxtCarousel));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public R delete(@RequestBody Long[] ids) {
        return toR(gjxtCarouselService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 删除
     */
    @GetMapping("/remove/{id}")
    public R deleteById(@PathVariable("id") Long id) {
        return toR(gjxtCarouselService.removeById(id));
    }
}

