package gjxt.portal.api.service;

import gjxt.portal.api.entity.GjxtWorkUpdate;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 工作动态 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtWorkUpdateService extends IService<GjxtWorkUpdate> {
    IPage<GjxtWorkUpdate> getWorkUpdateList(Page page, Map<String, Object> data);
    GjxtWorkUpdate getWorkUpdateById(Long id);
}
