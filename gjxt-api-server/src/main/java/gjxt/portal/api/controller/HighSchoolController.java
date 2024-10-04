package gjxt.portal.api.controller;


import gjxt.portal.api.common.base.R;
import gjxt.portal.api.service.HighSchoolService;
import gjxt.portal.api.entity.HighSchool;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户学校表 前端控制器
 * </p>
 *
 * @author hxy
 * @since 2023-05-15
 */

@RestController
@RequestMapping("/api/highSchool")
public class HighSchoolController extends BaseController{
    @Autowired
    private HighSchoolService highSchoolService;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @PostMapping("list/page")
    public R listPage(@RequestBody Map<String,Object> params) {
        return R.ok(highSchoolService.queryPage(getPage(params), params));
    }

    /**
     * 信息
     */
    @GetMapping("/getById/{id}")
            public R getById(@PathVariable("id") String id){
        return R.ok(highSchoolService.getById(id));
    }

    /**
    * 保存
    */
    @PostMapping("/add")
            public R save(@RequestBody HighSchool highSchool){
        return R.ok( highSchoolService.save(highSchool));
    }

    /**
     * 修改
     */
    @PostMapping("/edit")
            public R update(@RequestBody HighSchool highSchool){
        return R.ok(highSchoolService.updateById(highSchool));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
            public R delete(@RequestBody String[] ids){
        return R.ok(highSchoolService.removeByIds(Arrays.asList(ids)));
    }

/**
 * 删除
 */
    @GetMapping("/remove/{id}")
        public R deleteById(@PathVariable("id")  String id){
        return R.ok(highSchoolService.removeById(id));
    }
}

