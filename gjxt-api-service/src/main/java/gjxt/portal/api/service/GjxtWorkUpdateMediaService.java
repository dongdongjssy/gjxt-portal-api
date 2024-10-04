package gjxt.portal.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import gjxt.portal.api.entity.GjxtWorkUpdateMedia;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 工作动态关联媒体 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtWorkUpdateMediaService extends IService<GjxtWorkUpdateMedia> {
    IPage<GjxtWorkUpdateMedia> queryPage(Page page, Map<String, Object> data);

    List<GjxtWorkUpdateMedia> selectWorkUpdateMediaByWorkUpdateId(Long id);
}
