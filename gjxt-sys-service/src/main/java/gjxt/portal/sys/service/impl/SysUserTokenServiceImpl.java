package gjxt.portal.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.sys.entity.SysUserToken;
import gjxt.portal.sys.mapper.SysUserTokenMapper;
import gjxt.portal.sys.service.SysUserTokenService;
import org.springframework.stereotype.Service;


@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenMapper, SysUserToken> implements SysUserTokenService {
}
