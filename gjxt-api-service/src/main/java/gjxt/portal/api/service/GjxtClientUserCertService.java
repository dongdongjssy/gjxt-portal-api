package gjxt.portal.api.service;

import gjxt.portal.api.entity.GjxtCertificateSearchCriteria;
import gjxt.portal.api.entity.GjxtClientUserCert;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 * 客户端用户证书关联 服务类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtClientUserCertService extends IService<GjxtClientUserCert> {
    IPage<GjxtClientUserCert> queryPage(Page page, Map<String, Object> data);

    List<GjxtClientUserCert> searchCertificate(GjxtCertificateSearchCriteria searchCriteria);
}
