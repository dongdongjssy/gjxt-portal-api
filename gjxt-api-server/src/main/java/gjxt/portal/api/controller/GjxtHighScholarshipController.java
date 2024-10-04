package gjxt.portal.api.controller;


import gjxt.portal.api.common.base.R;
import gjxt.portal.api.service.GjxtHighScholarshipService;
import gjxt.portal.api.entity.GjxtHighScholarship;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 奖学金名单 前端控制器
 * </p>
 *
 * @author hxy
 * @since 2023-05-15
 */

@RestController
@RequestMapping("/api/gjxtHighScholarship")
public class GjxtHighScholarshipController extends BaseController{
    @Autowired
    private GjxtHighScholarshipService gjxtHighScholarshipService;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @PostMapping("list/page")
    public R listPage(@RequestBody Map<String,Object> params) {
        return R.ok(gjxtHighScholarshipService.queryPage(getPage(params), params));
    }

    /**
     * 信息
     */
    @GetMapping("/getById/{id}")
            public R getById(@PathVariable("id") Long id){
        return R.ok(gjxtHighScholarshipService.getById(id));
    }

    /**
    * 保存
    */
    @PostMapping("/add")
            public R save(@RequestBody GjxtHighScholarship gjxtHighScholarship){
        return R.ok( gjxtHighScholarshipService.save(gjxtHighScholarship));
    }

    /**
     * 修改
     */
    @PostMapping("/edit")
            public R update(@RequestBody GjxtHighScholarship gjxtHighScholarship){
        return R.ok(gjxtHighScholarshipService.updateById(gjxtHighScholarship));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
            public R delete(@RequestBody Long[] ids){
        return R.ok(gjxtHighScholarshipService.removeByIds(Arrays.asList(ids)));
    }

/**
 * 删除
 */
    @GetMapping("/remove/{id}")
        public R deleteById(@PathVariable("id")  Long id){
        return R.ok(gjxtHighScholarshipService.removeById(id));
    }
}

