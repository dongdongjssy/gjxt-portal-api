package gjxt.portal.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gjxt.portal.api.common.base.ResultCode;
import gjxt.portal.api.common.exception.BusinessException;
import gjxt.portal.api.common.utils.MybatisPlusUtil;
import gjxt.portal.api.entity.GjxtMessageBoard;
import gjxt.portal.api.mapper.GjxtMessageBoardMapper;
import gjxt.portal.api.service.GjxtMessageBoardService;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 获奖心声 服务实现类
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
@Service
public class GjxtMessageBoardServiceImpl extends ServiceImpl<GjxtMessageBoardMapper, GjxtMessageBoard> implements GjxtMessageBoardService {

    /**
     * 分页查询
     */
    @Override
    public IPage<GjxtMessageBoard> queryPage(Page page, Map<String, Object> map) {
        // 查询条件自己通过queryWrapper构建
        QueryWrapper<GjxtMessageBoard> queryWrapper = new QueryWrapper<>();
        GjxtMessageBoard gjxtMessageBoard = JSON.parseObject(JSON.toJSONString(map), GjxtMessageBoard.class);
        MybatisPlusUtil.notNullField(gjxtMessageBoard, queryWrapper);
        return baseMapper.selectMessageBoards(page, queryWrapper);
    }

    @Override
    public GjxtMessageBoard selectMessageBoardById(Long id) {
        GjxtMessageBoard messageBoard = baseMapper.selectMessageBoardById(id);
        if (Objects.isNull(messageBoard)) {
            throw new BusinessException(ResultCode.NOT_FOUND);
        }

        return messageBoard;
    }
}
