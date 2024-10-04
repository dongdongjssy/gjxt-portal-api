package gjxt.portal.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import gjxt.portal.api.entity.GjxtAnnouncementMedia;

import java.util.List;

/**
 * <p>
 * 通知公告关联媒体 Mapper 接口
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtAnnouncementMediaMapper extends BaseMapper<GjxtAnnouncementMedia> {
    List<GjxtAnnouncementMedia> selectAnnouncementMediaByAnnouncementId(Long id);
}
