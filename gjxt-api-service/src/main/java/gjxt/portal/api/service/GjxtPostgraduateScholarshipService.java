package gjxt.portal.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import gjxt.portal.api.entity.GjxtPostgraduateScholarship;

import java.util.Map;

/**
 * <p>
 * 奖学金名单 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-05-15
 */
public interface GjxtPostgraduateScholarshipService extends IService<GjxtPostgraduateScholarship> {
    IPage<GjxtPostgraduateScholarship> queryPage(Page page, Map<String, Object> data);
}
