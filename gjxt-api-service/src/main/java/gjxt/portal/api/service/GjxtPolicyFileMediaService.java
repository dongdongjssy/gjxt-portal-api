package gjxt.portal.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import gjxt.portal.api.entity.GjxtPolicyFileMedia;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 政策文件关联媒体 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtPolicyFileMediaService extends IService<GjxtPolicyFileMedia> {
    IPage<GjxtPolicyFileMedia> queryPage(Page page, Map<String, Object> data);

    List<GjxtPolicyFileMedia> selectPolicyFileMediaByPolicyFileId(Long id);
}
