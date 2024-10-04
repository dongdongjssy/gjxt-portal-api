package gjxt.portal.api.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import gjxt.portal.api.common.base.R;
import gjxt.portal.api.common.base.ResultCode;
import gjxt.portal.api.common.exception.BusinessException;
import gjxt.portal.api.entity.GjxtMultimedia;
import gjxt.portal.api.entity.GjxtPolicyFile;
import gjxt.portal.api.entity.GjxtPolicyFileMedia;
import gjxt.portal.api.service.GjxtPolicyFileMediaService;
import gjxt.portal.api.service.GjxtPolicyFileService;
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
 * 政策文件 前端控制器
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */

@RestController
@RequestMapping("/api/policyFile")
@RequiredArgsConstructor
public class GjxtPolicyFileController extends BaseController {
    private final GjxtPolicyFileService gjxtPolicyFileService;
    private final GjxtPolicyFileMediaService gjxtPolicyFileMediaService;

    /**
     * 分页查询
     */
    @PostMapping("/list")
    public R listPage(@RequestBody Map<String, Object> params) {
        IPage<GjxtPolicyFile> data = gjxtPolicyFileService.getPolicyFileList(getPage(params), params);
        return R.ok(data.getRecords()).put("total", data.getTotal());
    }

    /**
     * 信息
     */
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable("id") Long id) {
        GjxtPolicyFile data = gjxtPolicyFileService.getPolicyFileById(id);
        if (Objects.isNull(data)) {
            throw new BusinessException(ResultCode.NOT_FOUND);
        }

        List<GjxtMultimedia> medias = gjxtPolicyFileMediaService.selectPolicyFileMediaByPolicyFileId(data.getId())
                .stream().map(GjxtPolicyFileMedia::getMedia).collect(Collectors.toList());
        if (!medias.isEmpty()) {
            data.setMedias(medias);
        }
        return R.ok(data);
    }

    /**
     * 保存
     */
    @PostMapping("/add")
    public R save(@RequestBody GjxtPolicyFile gjxtPolicyFile) {
        return toR(gjxtPolicyFileService.save(gjxtPolicyFile));
    }

    /**
     * 修改
     */
    @PostMapping("/edit")
    public R update(@RequestBody GjxtPolicyFile gjxtPolicyFile) {
        return toR(gjxtPolicyFileService.updateById(gjxtPolicyFile));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public R delete(@RequestBody Long[] ids) {
        return toR(gjxtPolicyFileService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 删除
     */
    @GetMapping("/remove/{id}")
    public R deleteById(@PathVariable("id") Long id) {
        return toR(gjxtPolicyFileService.removeById(id));
    }
}

