package gjxt.portal.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
@TableName("sys_menu")
public class SysMenu implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 菜单ID
	 */
	@TableId(value = "menu_id", type = IdType.AUTO)
	private Long menuId;

	/**
	 * 父菜单ID，一级菜单为0
	 */
	private Long parentId;
	
	/**
	 * 父菜单名称
	 */
	@TableField(exist=false)
	private String parentName;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 菜单URL
	 */
	private String url;

	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	private String perms;

	/**
	 * 类型     0：目录   1：菜单   2：按钮
	 */
	private Integer type;

	/**
	 * 菜单图标
	 */
	private String icon;

	/**
	 * 排序
	 */
	private Integer orderNum;
	
	/**
	 * ztree属性
	 */
	@TableField(exist=false)
	private Boolean open;

	@TableField(exist=false)
	private List<SysMenu> children = new ArrayList<>();

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
