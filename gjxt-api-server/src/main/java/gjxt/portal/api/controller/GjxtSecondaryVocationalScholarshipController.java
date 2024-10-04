package gjxt.portal.api.controller;


import gjxt.portal.api.common.base.R;
import gjxt.portal.api.service.GjxtSecondaryVocationalScholarshipService;
import gjxt.portal.api.entity.GjxtSecondaryVocationalScholarship;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 中职奖学金名单 前端控制器
 * </p>
 *
 * @author hxy
 * @since 2023-05-15
 */

@RestController
@RequestMapping("/api/gjxtSecondaryVocationalScholarship")
public class GjxtSecondaryVocationalScholarshipController extends BaseController {
    @Autowired
    private GjxtSecondaryVocationalScholarshipService gjxtSecondaryVocationalScholarshipService;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @PostMapping("list/page")
    public R listPage(@RequestBody Map<String, Object> params) {
        return R.ok(gjxtSecondaryVocationalScholarshipService.queryPage(getPage(params), params));
    }

    /**
     * 信息
     */
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable("id") Long id) {
        return R.ok(gjxtSecondaryVocationalScholarshipService.getById(id));
    }

    /**
     * 保存
     */
    @PostMapping("/add")
    public R save(@RequestBody GjxtSecondaryVocationalScholarship gjxtSecondaryVocationalScholarship) {
        return R.ok(gjxtSecondaryVocationalScholarshipService.save(gjxtSecondaryVocationalScholarship));
    }

    /**
     * 修改
     */
    @PostMapping("/edit")
    public R update(@RequestBody GjxtSecondaryVocationalScholarship gjxtSecondaryVocationalScholarship) {
        return R.ok(gjxtSecondaryVocationalScholarshipService.updateById(gjxtSecondaryVocationalScholarship));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public R delete(@RequestBody Long[] ids) {
        return R.ok(gjxtSecondaryVocationalScholarshipService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 删除
     */
    @GetMapping("/remove/{id}")
    public R deleteById(@PathVariable("id") Long id) {
        return R.ok(gjxtSecondaryVocationalScholarshipService.removeById(id));
    }
}

