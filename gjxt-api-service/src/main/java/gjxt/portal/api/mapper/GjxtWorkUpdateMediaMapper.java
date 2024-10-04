package gjxt.portal.api.mapper;

import gjxt.portal.api.entity.GjxtWorkUpdateMedia;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 工作动态关联媒体 Mapper 接口
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtWorkUpdateMediaMapper extends BaseMapper<GjxtWorkUpdateMedia> {
    List<GjxtWorkUpdateMedia> selectWorkUpdateMediaByWorkUpdateId(Long id);
}
