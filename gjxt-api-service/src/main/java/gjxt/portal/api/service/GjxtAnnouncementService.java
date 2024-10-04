package gjxt.portal.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import gjxt.portal.api.entity.GjxtAnnouncement;

import java.util.Map;

/**
 * <p>
 * 通知公告 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtAnnouncementService extends IService<GjxtAnnouncement> {
    IPage<GjxtAnnouncement> getAnnouncementList(Page page, Map<String, Object> data);

    GjxtAnnouncement getAnnouncementById(Long id);
}
