package gjxt.portal.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import gjxt.portal.api.entity.GjxtCertificateSearchCriteria;
import gjxt.portal.api.entity.GjxtClientUserCert;

import java.util.List;

/**
 * <p>
 * 客户端用户证书关联 Mapper 接口
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtClientUserCertMapper extends BaseMapper<GjxtClientUserCert> {
    List<GjxtClientUserCert> searchCertificate(GjxtCertificateSearchCriteria searchCriteria);
}
