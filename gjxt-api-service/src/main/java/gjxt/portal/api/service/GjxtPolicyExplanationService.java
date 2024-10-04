package gjxt.portal.api.service;

import gjxt.portal.api.entity.GjxtPolicyExplanation;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 政策解读 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtPolicyExplanationService extends IService<GjxtPolicyExplanation> {
    IPage<GjxtPolicyExplanation> queryPage(Page page, Map<String, Object> data);
}
