package gjxt.portal.api.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import gjxt.portal.api.common.base.R;
import gjxt.portal.api.common.base.ResultCode;
import gjxt.portal.api.common.exception.BusinessException;
import gjxt.portal.api.entity.*;
import gjxt.portal.api.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 获奖证书 前端控制器
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */

@RestController
@RequestMapping("/api/certificate")
@RequiredArgsConstructor
public class GjxtCertificateController extends BaseController {
    private final GjxtCertificateService gjxtCertificateService;
    private final GjxtClientUserCertService gjxtClientUserCertService;
    private final GjxtHighScholarshipService highScholarshipService;
    private final GjxtSecondaryVocationalScholarshipService secondaryVocationalScholarshipService;
    private final GjxtPostgraduateScholarshipService postgraduateScholarshipService;

    @PostMapping("/search")
    public R search(@RequestBody Map<String, Object> params) {
        GjxtCertificateSearchCriteria searchCriteria =
                JSON.parseObject(JSON.toJSONString(params), GjxtCertificateSearchCriteria.class);

        if (!searchCriteria.checkCriteria()) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "查询条件不能全为空");
        }

        if(searchCriteria.getSearchIdNumber()!=null){
            params.put("identityId",searchCriteria.getSearchIdNumber());
        }

        if(searchCriteria.getSearchRealName()!=null){
            params.put("name",searchCriteria.getSearchRealName());
        }

        if(searchCriteria.getSearchCertNumber()!=null){
            params.put("certNumber",searchCriteria.getSearchCertNumber());
        }
        Page page = getPage(params);
        IPage<GjxtCertificate> gjxtCertificateIPage = gjxtCertificateService.queryPage(page, params);
        List<GjxtCertificate> records = gjxtCertificateIPage.getRecords();
        return R.ok(records).put("total", gjxtCertificateIPage.getTotal());
    }

    /**
     * 分页查询
     *
     * @param params
     */
    @PostMapping("/list")
    public R listPage(@RequestBody Map<String, Object> params) {
        return R.ok(gjxtCertificateService.queryPage(getPage(params), params));
    }

    /**
     * 信息
     */
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable("id") Long id) {
        return R.ok(gjxtCertificateService.getById(id));
    }

    /**
     * 保存
     */
    @PostMapping("/add")
    public R save(@RequestBody GjxtCertificate gjxtCertificate) {
        return R.ok(gjxtCertificateService.save(gjxtCertificate));
    }

    /**
     * 修改
     */
    @PostMapping("/edit")
    public R update(@RequestBody GjxtCertificate gjxtCertificate) {
        return R.ok(gjxtCertificateService.updateById(gjxtCertificate));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public R delete(@RequestBody Long[] ids) {
        return R.ok(gjxtCertificateService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 删除
     */
    @GetMapping("/remove/{id}")
    public R deleteById(@PathVariable("id") Long id) {
        return R.ok(gjxtCertificateService.removeById(id));
    }
}

