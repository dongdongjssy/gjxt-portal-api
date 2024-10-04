package gjxt.portal.api.service.impl;

import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.GjxtCertificate;
import gjxt.portal.api.mapper.GjxtCertificateMapper;
import gjxt.portal.api.service.GjxtCertificateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * <p>
 * 获奖证书 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Service
public class GjxtCertificateServiceImpl extends ServiceImpl<GjxtCertificateMapper, GjxtCertificate> implements GjxtCertificateService {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    @Override
    public IPage<GjxtCertificate> queryPage(Page page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<GjxtCertificate> queryWrapper = new QueryWrapper<>();
        GjxtCertificate gjxtCertificate = JSONObject.parseObject(JSONObject.toJSONString(map), GjxtCertificate.class);
        MybatisPlusUtil.notNullField(gjxtCertificate, queryWrapper);
        IPage<GjxtCertificate> gjxtCertificatePage = baseMapper.selectPage(page, queryWrapper);
        return gjxtCertificatePage;
    }
}
