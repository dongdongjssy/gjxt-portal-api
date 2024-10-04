package gjxt.portal.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import gjxt.portal.api.entity.GjxtMostAskedQuestions;

import java.util.Map;

/**
 * <p>
 * 常见问题 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtMostAskedQuestionsService extends IService<GjxtMostAskedQuestions> {
    IPage<GjxtMostAskedQuestions> queryPage(Page<GjxtMostAskedQuestions> page, Map<String, Object> data);
}
