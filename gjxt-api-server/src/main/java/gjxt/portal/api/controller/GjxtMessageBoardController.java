package gjxt.portal.api.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import gjxt.portal.api.common.base.R;
import gjxt.portal.api.entity.GjxtMessageBoard;
import gjxt.portal.api.service.GjxtMessageBoardService;
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

/**
 * <p>
 * 获奖心声 前端控制器
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */

@RestController
@RequestMapping("/api/messageBoard")
@RequiredArgsConstructor
public class GjxtMessageBoardController extends BaseController {
    private final GjxtMessageBoardService gjxtMessageBoardService;

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public R listPage(@RequestBody Map<String, Object> params) {
        IPage<GjxtMessageBoard> data = gjxtMessageBoardService.queryPage(getPage(params), params);
        return R.ok(data.getRecords()).put("total", data.getTotal());
    }

    /**
     * 我的心声
     */
    @PostMapping("/listMy")
    public R listMy(@RequestBody Map<String, Object> params) {
        IPage<GjxtMessageBoard> data = gjxtMessageBoardService.queryPage(getPage(params), params);
        return R.ok(data);
    }

    /**
     * 信息
     */
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable("id") Long id) {
        return R.ok(gjxtMessageBoardService.selectMessageBoardById(id));
    }

    /**
     * 保存
     */
    @PostMapping("/add")
    public R save(@Validated(value = Add.class) @RequestBody GjxtMessageBoard gjxtMessageBoard) {
        setModifyData(gjxtMessageBoard);
        return toR(gjxtMessageBoardService.save(gjxtMessageBoard));
    }

    /**
     * 修改
     */
    @PostMapping("/edit")
    public R update(@Validated(value = Update.class) @RequestBody GjxtMessageBoard gjxtMessageBoard) {
        return toR(gjxtMessageBoardService.updateById(gjxtMessageBoard));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public R delete(@RequestBody Long[] ids) {
        return toR(gjxtMessageBoardService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 删除
     */
    @GetMapping("/remove/{id}")
    public R deleteById(@PathVariable("id") Long id) {
        return toR(gjxtMessageBoardService.removeById(id));
    }
}

