package gjxt.portal.api.service;

import gjxt.portal.api.entity.ClientUser;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 客户端用户 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-02-23
 */
public interface ClientUserService extends IService<ClientUser> {
    IPage<ClientUser> selectClientUsers(Page page, Map<String, Object> data);

    ClientUser getClientUserById(Long id);
}
