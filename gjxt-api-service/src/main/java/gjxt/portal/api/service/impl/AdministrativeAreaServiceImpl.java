package gjxt.portal.api.service.impl;

import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.AdministrativeArea;
import gjxt.portal.api.mapper.AdministrativeAreaMapper;
import gjxt.portal.api.service.AdministrativeAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * <p>
 * 行政区域表 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-05-15
 */
@Service
public class AdministrativeAreaServiceImpl extends ServiceImpl<AdministrativeAreaMapper, AdministrativeArea>implements AdministrativeAreaService {

        /**
         * 分页查询
         * @param map
         * @return
         */
        @Override
        public IPage<AdministrativeArea>queryPage(Page page,Map<String,Object>map){
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<AdministrativeArea>queryWrapper=new QueryWrapper<>();
        AdministrativeArea administrativeArea =JSONObject.parseObject(JSONObject.toJSONString(map), AdministrativeArea.class);
        MybatisPlusUtil.notNullField(administrativeArea,queryWrapper);
        IPage<AdministrativeArea> administrativeAreaPage=baseMapper.selectPage(page,queryWrapper);
        return administrativeAreaPage;
        }
}
