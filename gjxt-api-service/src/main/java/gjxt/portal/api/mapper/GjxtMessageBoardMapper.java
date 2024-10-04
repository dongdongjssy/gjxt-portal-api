package gjxt.portal.api.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import gjxt.portal.api.entity.GjxtMessageBoard;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 获奖心声 Mapper 接口
 * </p>
 *
 * @author hxy
 * @since 2023-04-27
 */
public interface GjxtMessageBoardMapper extends BaseMapper<GjxtMessageBoard> {
    IPage<GjxtMessageBoard> selectMessageBoards(
            IPage<GjxtMessageBoard> page,
            @Param(Constants.WRAPPER) QueryWrapper<GjxtMessageBoard> wrapper
    );

    GjxtMessageBoard selectMessageBoardById(Long id);
}
