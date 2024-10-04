package gjxt.portal.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import gjxt.portal.sys.entity.SysUserToken;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysUserTokenMapper extends BaseMapper<SysUserToken> {

    SysUserToken queryByToken(String token);
	
}
