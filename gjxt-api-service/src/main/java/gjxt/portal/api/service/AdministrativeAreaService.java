package gjxt.portal.api.service;

import gjxt.portal.api.entity.AdministrativeArea;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 行政区域表 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-05-15
 */
public interface AdministrativeAreaService extends IService<AdministrativeArea> {
    IPage<AdministrativeArea> queryPage(Page page, Map<String, Object> data);
}
