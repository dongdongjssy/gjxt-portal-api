package gjxt.portal.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import gjxt.portal.api.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * 系统用户Token
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("sys_user_token")
public class SysUserToken extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//用户ID
	@TableId(type = IdType.INPUT)
	private Long userId;
	//token
	private String token;
	//过期时间
	private LocalDateTime expireTime;

	@TableField("status")
	private Integer status;

	@TableField("create_id")
	private Long createId;

	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private LocalDateTime createTime;

	@TableField("modify_id")
	private Long modifyId;

	@TableField("modify_time")
	private LocalDateTime modifyTime;

}
