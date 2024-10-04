package gjxt.portal.api.service;

import gjxt.portal.api.entity.GjxtCertificate;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 获奖证书 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtCertificateService extends IService<GjxtCertificate> {
    IPage<GjxtCertificate> queryPage(Page page, Map<String, Object> data);
}
