package gjxt.portal.api.service;

import gjxt.portal.api.entity.GjxtMultimedia;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 多媒体文件 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtMultimediaService extends IService<GjxtMultimedia> {
    IPage<GjxtMultimedia> queryPage(Page page, Map<String, Object> data);
}
