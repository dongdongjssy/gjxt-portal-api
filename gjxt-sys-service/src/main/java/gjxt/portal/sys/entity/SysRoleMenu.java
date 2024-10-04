package gjxt.portal.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import gjxt.portal.api.common.base.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色与菜单对应关系
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 菜单ID
	 */
	private Long menuId;

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
