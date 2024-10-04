package gjxt.portal.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import gjxt.portal.api.entity.GjxtAnnouncementMedia;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 通知公告关联媒体 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtAnnouncementMediaService extends IService<GjxtAnnouncementMedia> {
    IPage<GjxtAnnouncementMedia> queryPage(Page page, Map<String, Object> data);

    List<GjxtAnnouncementMedia> selectAnnouncementMediaByAnnouncementId(Long id);

}
