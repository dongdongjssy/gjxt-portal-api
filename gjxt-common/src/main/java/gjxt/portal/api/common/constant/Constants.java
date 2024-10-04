package gjxt.portal.api.common.constant;

/**
 * 通用常量信息
 * 
 * @author goose
 */
public class Constants
{

	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;

	/** 资料修改次数 */
	public static final int MODIFY_COUNT= 3;

	/**
	 * 存放CMS的内容里的文件的基准路径，相对于${repository_path}路径
	 */
	public static final String BASE_CMS_FILE = "cms/files";

	/**
	 * 存放CMS的内容里图片的基准路径，相对于${repository_path}路径
	 */
	public static final String BASE_CMS_PIC = "cms/picture";

	/**
	 * 存放CMS的内容里视频的基准路径，相对于${repository_path}路径
	 */
	public static final String BASE_CMS_VIO = "cms/video";

	/**
	 * UTF-8 字符集
	 */
	public static final String UTF8 = "UTF-8";

	/**
	 * 通用成功标识
	 */
	public static final String SUCCESS = "0";

	/**
	 * 通用失败标识
	 */
	public static final String FAIL = "1";

	/**
	 * 登录成功
	 */
	public static final String LOGIN_SUCCESS = "Success";

	/**
	 * 注销
	 */
	public static final String LOGOUT = "Logout";

	/**
	 * 登录失败
	 */
	public static final String LOGIN_FAIL = "Error";

	/**
	 * 自动去除表前缀
	 */
	public static String AUTO_REOMVE_PRE = "true";

	/**
	 * 当前记录起始索引
	 */
	public static String PAGE_NUM = "pageNum";

	/**
	 * 每页显示记录数
	 */
	public static String PAGE_SIZE = "pageSize";

	/**
	 * 排序列
	 */
	public static String ORDER_BY_COLUMN = "orderByColumn";

	/**
	 * 排序的方向 "desc" 或者 "asc".
	 */
	public static String IS_ASC = "isAsc";


	/**
	 * 身份验证验证码模板
	 */
	public static String TMPLATE1 = "SMS_267000098";

	/**
	 * 登录确认验证码
	 */
	public static String TMPLATE2 = "SMS_208780085"; 

	/**
	 *  登录异常验证码
	 */
	public static String TMPLATE3 = "SMS_208780084"; 

	/**
	 * 用户注册验证码
	 */
	public static String TMPLATE4 = "SMS_208780083"; 

	/**
	 *  修改密码验证码
	 */
	public static String TMPLATE5 = "SMS_208780082"; 

	/**
	 *  信息变更验证码
	 */
	public static String TMPLATE6 = "SMS_208780081";

	public static String USER_ID = "userId";
}
