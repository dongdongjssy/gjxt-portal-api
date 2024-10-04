package gjxt.portal.api.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import gjxt.portal.api.common.base.R;
import gjxt.portal.api.common.base.ResultCode;
import gjxt.portal.api.common.exception.BusinessException;
import gjxt.portal.api.entity.GjxtMultimedia;
import gjxt.portal.api.entity.GjxtWorkUpdate;
import gjxt.portal.api.entity.GjxtWorkUpdateMedia;
import gjxt.portal.api.service.GjxtWorkUpdateMediaService;
import gjxt.portal.api.service.GjxtWorkUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 工作动态 前端控制器
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */

@RestController
@RequestMapping("/api/workUpdate")
@RequiredArgsConstructor
public class GjxtWorkUpdateController extends BaseController {
    private final GjxtWorkUpdateService gjxtWorkUpdateService;
    private final GjxtWorkUpdateMediaService gjxtWorkUpdateMediaService;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @PostMapping("/list")
    public R listPage(@RequestBody Map<String, Object> params) {
        IPage<GjxtWorkUpdate> data = gjxtWorkUpdateService.getWorkUpdateList(getPage(params), params);
        return R.ok(data.getRecords()).put("total", data.getTotal());
    }

    /**
     * 信息
     */
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable("id") Long id) {
        GjxtWorkUpdate data = gjxtWorkUpdateService.getWorkUpdateById(id);
        if (Objects.isNull(data)) {
            throw new BusinessException(ResultCode.NOT_FOUND);
        }

        List<GjxtMultimedia> medias = gjxtWorkUpdateMediaService.selectWorkUpdateMediaByWorkUpdateId(data.getId())
                .stream().map(GjxtWorkUpdateMedia::getMedia).collect(Collectors.toList());
        if (!medias.isEmpty()) {
            data.setMedias(medias);
        }

        return R.ok(data);
    }

    /**
     * 保存
     */
    @PostMapping("/add")
    public R save(@RequestBody GjxtWorkUpdate gjxtWorkUpdate) {
        setModifyData(gjxtWorkUpdate);
        return toR(gjxtWorkUpdateService.save(gjxtWorkUpdate));
    }

    /**
     * 修改
     */
    @PostMapping("/edit")
    public R update(@RequestBody GjxtWorkUpdate gjxtWorkUpdate) {
        return toR(gjxtWorkUpdateService.updateById(gjxtWorkUpdate));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public R delete(@RequestBody Long[] ids) {
        return toR(gjxtWorkUpdateService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 删除
     */
    @GetMapping("/remove/{id}")
    public R deleteById(@PathVariable("id") Long id) {
        return toR(gjxtWorkUpdateService.removeById(id));
    }
}

