package gjxt.portal.api.service.impl;

import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.HighSchool;
import gjxt.portal.api.mapper.HighSchoolMapper;
import gjxt.portal.api.service.HighSchoolService;
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
public class HighSchoolServiceImpl extends ServiceImpl<HighSchoolMapper, HighSchool>implements HighSchoolService {

        /**
         * 分页查询
         * @param map
         * @return
         */
        @Override
        public IPage<HighSchool>queryPage(Page page,Map<String,Object>map){
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<HighSchool>queryWrapper=new QueryWrapper<>();
        HighSchool highSchool =JSONObject.parseObject(JSONObject.toJSONString(map), HighSchool.class);
        MybatisPlusUtil.notNullField(highSchool,queryWrapper);
        IPage<HighSchool> highSchoolPage=baseMapper.selectPage(page,queryWrapper);
        return highSchoolPage;
        }
}
