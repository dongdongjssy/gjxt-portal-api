package gjxt.portal.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import gjxt.portal.api.common.base.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("sys_role")
public class SysRole extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 角色ID
	 */
	@TableId(value = "role_id", type = IdType.AUTO)
	private Long roleId;

	/**
	 * 角色名称
	 */
	@NotBlank(message="角色名称不能为空")
	private String roleName;
	
	/**
	 * 人员数量
	 */
	private int userNumber;
	
	/**
	 * 角色描述
	 */
	private String description;
	
	/**
	 * 备注
	 */
	private String remark;
	
	@TableField(exist=false)
	private List<Long> menuIdList;

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
