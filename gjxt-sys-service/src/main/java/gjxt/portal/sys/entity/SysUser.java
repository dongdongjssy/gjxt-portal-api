package gjxt.portal.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import gjxt.portal.api.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("sys_user")
public class SysUser extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	@TableId(value = "user_id", type = IdType.AUTO)
	private Long userId;

	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;

	/**
	 * 角色ID列表
	 */
	@TableField(exist=false)
	private List<Long> roleIdList;

	/**
	 * 角色ID列表
	 */
	@TableField(exist=false)
	private Long roleId;

	/**
	 * 角色/职务
	 */
	@TableField(exist=false)
	private List<SysRole> roleList;

	@TableField(exist=false)
	private String roleNames;

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

	private Date passwordResetDate;

	private String nickName;

	private String userNum;
}
