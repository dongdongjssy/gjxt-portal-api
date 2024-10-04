package gjxt.portal.api.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import gjxt.portal.api.entity.ClientUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 客户端用户 Mapper 接口
 * </p>
 *
 * @author hxy
 * @since 2023-02-23
 */
public interface ClientUserMapper extends BaseMapper<ClientUser> {
    IPage<ClientUser> selectClientUsers(Page page, @Param(Constants.WRAPPER) QueryWrapper<ClientUser> queryWrapper);

    ClientUser getClientUserById(Long id);
}
