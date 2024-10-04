package gjxt.portal.api.controller;


import gjxt.portal.api.common.base.R;
import gjxt.portal.api.service.AdministrativeAreaService;
import gjxt.portal.api.entity.AdministrativeArea;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 行政区域表 前端控制器
 * </p>
 *
 * @author hxy
 * @since 2023-05-15
 */

@RestController
@RequestMapping("/api/administrativeArea")
public class AdministrativeAreaController extends BaseController {
    @Autowired
    private AdministrativeAreaService administrativeAreaService;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @PostMapping("list/page")
    public R listPage(@RequestBody Map<String, Object> params) {
        return R.ok(administrativeAreaService.queryPage(getPage(params), params));
    }

    /**
     * 信息
     */
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable("id") String id) {
        return R.ok(administrativeAreaService.getById(id));
    }

    /**
     * 保存
     */
    @PostMapping("/add")
    public R save(@RequestBody AdministrativeArea administrativeArea) {
        return R.ok(administrativeAreaService.save(administrativeArea));
    }

    /**
     * 修改
     */
    @PostMapping("/edit")
    public R update(@RequestBody AdministrativeArea administrativeArea) {
        return R.ok(administrativeAreaService.updateById(administrativeArea));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public R delete(@RequestBody String[] ids) {
        return R.ok(administrativeAreaService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 删除
     */
    @GetMapping("/remove/{id}")
    public R deleteById(@PathVariable("id") String id) {
        return R.ok(administrativeAreaService.removeById(id));
    }
}

