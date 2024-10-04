package gjxt.portal.api.controller;


import gjxt.portal.api.common.base.R;
import gjxt.portal.api.service.GjxtMultimediaService;
import gjxt.portal.api.entity.GjxtMultimedia;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 多媒体文件 前端控制器
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */

@RestController
@RequestMapping("/api/gjxtMultimedia")
public class GjxtMultimediaController extends BaseController {
    @Autowired
    private GjxtMultimediaService gjxtMultimediaService;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @PostMapping("list/page")
    public R listPage(@RequestBody Map<String, Object> params) {
        return R.ok(gjxtMultimediaService.queryPage(getPage(params), params));
    }

    /**
     * 信息
     */
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable("id") Long id) {
        return R.ok(gjxtMultimediaService.getById(id));
    }

    /**
     * 保存
     */
    @PostMapping("/add")
    public R save(@RequestBody GjxtMultimedia gjxtMultimedia) {
        return R.ok(gjxtMultimediaService.save(gjxtMultimedia));
    }

    /**
     * 修改
     */
    @PostMapping("/edit")
    public R update(@RequestBody GjxtMultimedia gjxtMultimedia) {
        return R.ok(gjxtMultimediaService.updateById(gjxtMultimedia));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public R delete(@RequestBody Long[] ids) {
        return R.ok(gjxtMultimediaService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 删除
     */
    @GetMapping("/remove/{id}")
    public R deleteById(@PathVariable("id") Long id) {
        return R.ok(gjxtMultimediaService.removeById(id));
    }
}

