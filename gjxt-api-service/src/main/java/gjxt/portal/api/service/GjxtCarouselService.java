package gjxt.portal.api.service;

import gjxt.portal.api.entity.GjxtCarousel;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 轮播图 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtCarouselService extends IService<GjxtCarousel> {
    IPage<GjxtCarousel> queryPage(Page page, Map<String, Object> data);

    void checkOrder(GjxtCarousel carousel);
}
