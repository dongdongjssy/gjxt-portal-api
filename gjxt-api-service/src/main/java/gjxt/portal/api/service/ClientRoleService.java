package gjxt.portal.api.service;

import gjxt.portal.api.entity.ClientRole;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 客户端角色 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-02-23
 */
public interface ClientRoleService extends IService<ClientRole> {
    IPage<ClientRole> queryPage(Page page, Map<String, Object> data);
}
