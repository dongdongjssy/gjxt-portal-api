package gjxt.portal.api.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import gjxt.portal.api.entity.GjxtPolicyFile;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 政策文件 Mapper 接口
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtPolicyFileMapper extends BaseMapper<GjxtPolicyFile> {
    IPage<GjxtPolicyFile> getPolicyFileList(Page page, @Param(Constants.WRAPPER) QueryWrapper<GjxtPolicyFile> queryWrapper);

    GjxtPolicyFile getPolicyFileById(Long id);
}
