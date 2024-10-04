package gjxt.portal.api.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import gjxt.portal.api.common.base.R;
import gjxt.portal.api.common.base.ResultCode;
import gjxt.portal.api.common.exception.BusinessException;
import gjxt.portal.api.entity.ClientUser;
import gjxt.portal.api.service.ClientUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 客户端用户 前端控制器
 * </p>
 *
 * @author hxy
 * @since 2023-05-04
 */

@RestController
@RequestMapping("/api/clientUser")
@RequiredArgsConstructor
public class ClientUserController extends BaseController {
    private final ClientUserService clientUserService;

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    @PostMapping("/list")
    public R listPage(@RequestBody Map<String, Object> params) {
        IPage<ClientUser> data = clientUserService.selectClientUsers(getPage(params), params);
        return R.ok(data.getRecords()).put("total", data.getTotal());
    }

    /**
     * 信息
     */
    @GetMapping("/getById/{id}")
    public R getById(@PathVariable("id") Long id) {
        ClientUser data = clientUserService.getClientUserById(id);
        if (Objects.isNull(data)) {
            throw new BusinessException(ResultCode.NOT_FOUND);
        }
        return R.ok(data);
    }

    /**
     * 保存
     */
    @PostMapping("/add")
    public R save(@RequestBody ClientUser clientUser) {
        return R.ok(clientUserService.save(clientUser));
    }

    /**
     * 修改
     */
    @PostMapping("/edit")
    public R update(@RequestBody ClientUser clientUser) {
        return toR(clientUserService.updateById(clientUser));
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    public R delete(@RequestBody Long[] ids) {
        return toR(clientUserService.removeByIds(Arrays.asList(ids)));
    }

    /**
     * 删除
     */
    @GetMapping("/remove/{id}")
    public R deleteById(@PathVariable("id") Long id) {
        return toR(clientUserService.removeById(id));
    }
}

