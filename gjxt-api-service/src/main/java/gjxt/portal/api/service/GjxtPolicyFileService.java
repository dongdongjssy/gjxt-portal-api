package gjxt.portal.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import gjxt.portal.api.entity.GjxtPolicyFile;

import java.util.Map;

/**
 * <p>
 * 政策文件 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtPolicyFileService extends IService<GjxtPolicyFile> {
    IPage<GjxtPolicyFile> getPolicyFileList(Page page, Map<String, Object> data);

    GjxtPolicyFile getPolicyFileById(Long id);
}
