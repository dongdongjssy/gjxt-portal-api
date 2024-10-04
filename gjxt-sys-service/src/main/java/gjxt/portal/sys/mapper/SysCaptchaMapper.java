package gjxt.portal.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import gjxt.portal.sys.entity.SysCaptcha;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 *
 * @author Mark sunlightcs@gmail.com
 */
@Mapper
public interface SysCaptchaMapper extends BaseMapper<SysCaptcha> {

}
