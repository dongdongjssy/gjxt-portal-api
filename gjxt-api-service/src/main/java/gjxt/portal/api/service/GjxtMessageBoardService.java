package gjxt.portal.api.service;

import gjxt.portal.api.entity.GjxtMessageBoard;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 获奖心声 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtMessageBoardService extends IService<GjxtMessageBoard> {
    IPage<GjxtMessageBoard> queryPage(Page page, Map<String, Object> data);
    GjxtMessageBoard selectMessageBoardById(Long id);
}
