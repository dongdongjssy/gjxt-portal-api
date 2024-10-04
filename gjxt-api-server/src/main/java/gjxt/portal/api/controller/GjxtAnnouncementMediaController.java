package gjxt.portal.api.controller;


import gjxt.portal.api.common.base.R;
import gjxt.portal.api.service.GjxtAnnouncementMediaService;
import gjxt.portal.api.entity.GjxtAnnouncementMedia;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 通知公告关联媒体 前端控制器
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */

@RestController
@RequestMapping("/api/gjxtAnnouncementMedia")
public class GjxtAnnouncementMediaController extends BaseController {
    @Autowired
    private GjxtAnnouncementMediaService gjxtAnnouncementMediaService;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @PostMapping("list/page")
    public R listPage(@RequestBody Map<String, Object> params) {
        return R.ok(gjxtAnnouncementMediaService.queryPage(getPage(params), params));
    }

    /**
     * 信息
     */
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable("id") Integer id) {
        return R.ok(gjxtAnnouncementMediaService.getById(id));
    }

    /**
     * 保存
     */
    @PostMapping("/add")
    public R save(@RequestBody GjxtAnnouncementMedia gjxtAnnouncementMedia) {
        return R.ok(gjxtAnnouncementMediaService.save(gjxtAnnouncementMedia));
    }

    /**
     * 修改
     */
    @PostMapping("/edit")
    public R update(@RequestBody GjxtAnnouncementMedia gjxtAnnouncementMedia) {
        return R.ok(gjxtAnnouncementMediaService.updateById(gjxtAnnouncementMedia));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public R delete(@RequestBody Integer[] ids) {
        return R.ok(gjxtAnnouncementMediaService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 删除
     */
    @GetMapping("/remove/{id}")
    public R deleteById(@PathVariable("id") Integer id) {
        return R.ok(gjxtAnnouncementMediaService.removeById(id));
    }
}

