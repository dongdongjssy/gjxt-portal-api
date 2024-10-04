package gjxt.portal.sys.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.code.kaptcha.Producer;
import gjxt.portal.api.common.exception.BusinessException;
import gjxt.portal.sys.entity.SysCaptcha;
import gjxt.portal.sys.mapper.SysCaptchaMapper;
import gjxt.portal.sys.service.SysCaptchaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 验证码
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysCaptchaService")
public class SysCaptchaServiceImpl extends ServiceImpl<SysCaptchaMapper, SysCaptcha> implements SysCaptchaService {
    @Resource
    private Producer producer;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if(StringUtils.isBlank(uuid)){
            throw new BusinessException("uuid不能为空");
        }
        SysCaptcha byId = this.getById(uuid);
        if(byId!=null){
            throw new BusinessException("uuid已存在");
        }
        //生成文字验证码
        String code = producer.createText();
        SysCaptcha captchaEntity = new SysCaptcha();
        captchaEntity.setUuid(uuid);
        captchaEntity.setCode(code);
        //5分钟后过期
        captchaEntity.setExpireTime(LocalDateTime.now().plusMinutes(5));
        this.save(captchaEntity);
        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        SysCaptcha captchaEntity = this.getOne(new QueryWrapper<SysCaptcha>().eq("uuid", uuid));
        if(captchaEntity == null){
            return false;
        }
        //删除验证码
        this.removeById(uuid);
        if(captchaEntity.getCode().equalsIgnoreCase(code) && captchaEntity.getExpireTime().isAfter(LocalDateTime.now())){
            return true;
        }

        return false;
    }
}
