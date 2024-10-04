package gjxt.portal.api.service.impl;

import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.SecondaryVocationalSchool;
import gjxt.portal.api.mapper.SecondaryVocationalSchoolMapper;
import gjxt.portal.api.service.SecondaryVocationalSchoolService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * <p>
 * 用户学校表 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-05-15
 */
@Service
public class SecondaryVocationalSchoolServiceImpl extends ServiceImpl<SecondaryVocationalSchoolMapper, SecondaryVocationalSchool>implements SecondaryVocationalSchoolService {

        /**
         * 分页查询
         * @param map
         * @return
         */
        @Override
        public IPage<SecondaryVocationalSchool>queryPage(Page page,Map<String,Object>map){
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<SecondaryVocationalSchool>queryWrapper=new QueryWrapper<>();
        SecondaryVocationalSchool secondaryVocationalSchool =JSONObject.parseObject(JSONObject.toJSONString(map), SecondaryVocationalSchool.class);
        MybatisPlusUtil.notNullField(secondaryVocationalSchool,queryWrapper);
        IPage<SecondaryVocationalSchool> secondaryVocationalSchoolPage=baseMapper.selectPage(page,queryWrapper);
        return secondaryVocationalSchoolPage;
        }
}
