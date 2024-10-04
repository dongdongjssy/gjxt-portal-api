package gjxt.portal.api.service;

import gjxt.portal.api.entity.ClientUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 客户端用户角色关联 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-02-23
 */
public interface ClientUserRoleService extends IService<ClientUserRole> {
    IPage<ClientUserRole> queryPage(Page page, Map<String, Object> data);
}
