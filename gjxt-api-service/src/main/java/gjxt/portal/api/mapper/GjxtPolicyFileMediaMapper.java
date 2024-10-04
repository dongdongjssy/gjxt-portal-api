package gjxt.portal.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import gjxt.portal.api.entity.GjxtPolicyFileMedia;

import java.util.List;

/**
 * <p>
 * 政策文件关联媒体 Mapper 接口
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtPolicyFileMediaMapper extends BaseMapper<GjxtPolicyFileMedia> {
    List<GjxtPolicyFileMedia> selectPolicyFileMediaByPolicyFileId(Long id);
}
