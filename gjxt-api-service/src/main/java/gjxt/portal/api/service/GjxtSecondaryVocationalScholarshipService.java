package gjxt.portal.api.service;

import gjxt.portal.api.entity.GjxtSecondaryVocationalScholarship;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 中职奖学金名单 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-05-15
 */
public interface GjxtSecondaryVocationalScholarshipService extends IService<GjxtSecondaryVocationalScholarship> {
    IPage<GjxtSecondaryVocationalScholarship> queryPage(Page page, Map<String, Object> data);
}
