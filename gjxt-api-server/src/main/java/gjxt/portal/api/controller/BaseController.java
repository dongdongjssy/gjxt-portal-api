package gjxt.portal.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import gjxt.portal.api.common.base.R;
import gjxt.portal.api.common.base.ResultCode;
import gjxt.portal.api.common.constant.Constants;
import gjxt.portal.api.common.exception.BusinessException;
import gjxt.portal.api.entity.GjxtBaseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Controller公共组件
 */
public class BaseController {
    @Autowired
    private HttpServletRequest request;

    protected Page getPage(Map<String, Object> params) {
        List<OrderItem> orders = new ArrayList<>();
        JSONObject pageInfo;
        if (params.get("page") != null) {
            pageInfo = JSON.parseObject(JSON.toJSONString(params.get("page")));
            JSONArray orders1 = pageInfo.getJSONArray("orders");
            if (orders1 != null) {
                for (int i = 0; i < orders1.size(); i++) {
                    JSONObject j = orders1.getJSONObject(i);
                    OrderItem item = new OrderItem();
                    item.setAsc(j.getBoolean("asc"));
                    item.setColumn(j.getString("column"));
                    orders.add(item);
                }
            }
        } else {
            return new Page<>(1, 1000);
        }
        Page page = new Page<>(pageInfo.getLongValue("current"), pageInfo.getLongValue("size"));
        page.addOrder(orders);
        return page;
    }

    protected Object getParam(Map<String, Object> params, String paramName) {
        if (!Objects.isNull(params.get(paramName))) {
            return JSON.parseObject(JSON.toJSONString(params.get(paramName)));
        }

        return null;
    }

    protected R toR(int rows) {
        return rows > 0 ? R.ok() : R.error();
    }

    protected R toR(boolean success) {
        return success ? R.ok() : R.error(ResultCode.FAIL);
    }

    protected Long getUserIdFromHeader() {
        Enumeration<?> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();

            if (Constants.USER_ID.equalsIgnoreCase(key)) {
                String userIdStr = request.getHeader(key);

                if (StringUtils.isBlank(userIdStr)) {
                    throw new BusinessException(ResultCode.USER_ID_MISSING_IN_HEADER);
                }

                return Long.valueOf(request.getHeader(key));
            }
        }

        return null;
    }

    /**
     * 当新添加一个实体对象时，将修改时间、修改用户设成和创建时间、创建用户一致。
     */
    protected void setModifyData(GjxtBaseEntity entity) {
        entity.setModifyBy(entity.getCreateBy());
        entity.setModifyTime(entity.getCreateTime());
    }
}
