package gjxt.portal.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.GjxtPolicyFile;
import gjxt.portal.api.mapper.GjxtPolicyFileMapper;
import gjxt.portal.api.service.GjxtPolicyFileService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 政策文件 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Service
public class GjxtPolicyFileServiceImpl extends ServiceImpl<GjxtPolicyFileMapper, GjxtPolicyFile> implements GjxtPolicyFileService {

    /**
     * 分页查询
     *
     * @param map
     * @return
     */
    @Override
    public IPage<GjxtPolicyFile> getPolicyFileList(Page page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<GjxtPolicyFile> queryWrapper = new QueryWrapper<>();
        GjxtPolicyFile gjxtPolicyFile = JSON.parseObject(JSON.toJSONString(map), GjxtPolicyFile.class);
        MybatisPlusUtil.notNullField(gjxtPolicyFile, queryWrapper);
        return baseMapper.getPolicyFileList(page, queryWrapper);
    }

    @Override
    public GjxtPolicyFile getPolicyFileById(Long id) {
        return baseMapper.getPolicyFileById(id);
    }
}
