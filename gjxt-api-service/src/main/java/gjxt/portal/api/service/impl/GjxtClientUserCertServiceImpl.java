package gjxt.portal.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.GjxtCertificateSearchCriteria;
import gjxt.portal.api.entity.GjxtClientUserCert;
import gjxt.portal.api.mapper.GjxtClientUserCertMapper;
import gjxt.portal.api.service.GjxtClientUserCertService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户端用户证书关联 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Service
public class GjxtClientUserCertServiceImpl extends ServiceImpl<GjxtClientUserCertMapper, GjxtClientUserCert> implements GjxtClientUserCertService {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    @Override
    public IPage<GjxtClientUserCert> queryPage(Page page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<GjxtClientUserCert> queryWrapper = new QueryWrapper<>();
        GjxtClientUserCert gjxtClientUserCert = JSON.parseObject(JSON.toJSONString(map), GjxtClientUserCert.class);
        MybatisPlusUtil.notNullField(gjxtClientUserCert, queryWrapper);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public List<GjxtClientUserCert> searchCertificate(GjxtCertificateSearchCriteria searchCriteria) {
        return baseMapper.searchCertificate(searchCriteria);
    }
}
