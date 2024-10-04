package gjxt.portal.api.common.base;


/**
 *
 */
public enum ResultCode implements IResultCode {

    /**
     * 成功
     */
    OK(200, "成功"),
    /**
     * 失败
     */
    ERROR(500, "失败"),

    SUCCESS(200, "成功"),
    FAIL(500, "失败"),
    VERIFY_CODE_CHECK_ERROR(1001111, "验证码校验失败"),
    VERIFY_CODE_EXPIRE_ERROR(1001112, "验证码过期"),
    LOGIN_NAME_EXISTS_ERROR(1001113, "登录名已经存在"),
    LOGIN_ERROR(401, "登录名或密码错误"),
    LOGIN_ACCOUNT_NOT_ACTIVE(1001115, "账号未激活，请联系工作人员"),
    LOGIN_ACCOUNT_LOCKED(1001115, "账号被锁定，请联系工作人员"),
    NOT_LOGIN(1001116, "用户未登录"),
    OLD_PWD_ERROR(1001117, "原密码不正确"),
    NOT_FOUND_ERROR(1001118, "请求记录不存在"),
    NOT_TECHNICIAN_ROLE(1001119, "用户非技术人员"),

    BAD_REQUEST(400, "参数或者语法不对"),
    USER_ID_MISSING_IN_HEADER(400, "请求头中缺少用户ID"),
    UNAUTHORIZED(401, "认证失败"),
    BAD_GETUSERINFO(401, "获取用户信息错误"),
    LOGIN_TIME_ERROR(401, "登陆失败，临时时间已过"),
    BAD_WECHAT_AUTH(402, "微信授权失败"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "请求的资源不存在"),
    OPERATE_ERROR(405, "操作失败，请求操作的资源不存在"),
    TIME_OUT(408, "请求超时"),

    SERVER_ERROR(500, "服务器内部错误"),

    USER_EXISTS(-100, "用户已存在"),
    USER_OR_PASSWORD_EMPTY(-101, "用户名或密码为空"),
    USER_ROLE_LIMITTED(-102, "用户没有角色"),
    USER_NOT_EXISTS(-103, "用户不存在"),
    NOT_ATTENTION_YUANER(-104, "请先关注【院儿】公众号并重新登陆小程序"),
    TOKEN_EMPTY(-105, "Token 为空"),
    TOKEN_EXPIRED(-106, "Token 失效"),
    PHONE_NOT_EXISTS(-107, "手机号不能为空"),
    PHONE_EXISTS(-108, "手机号已存在"),
    EMAIL_FAILURE(-109, "发送EMAIL失败"),
    EXCEPTION_CODE_NOT_CORRECT(-110, "验证码错误，请重新输入或重新获取"),
    EXCEPTION_SEND_SMS_CODE(-110, "验证码发送失败，请重试"),
    EXCEPTION_PHONE_OR_OPENID_EMPTY(-111, "手机号和微信授权标识不能为空"),
    EXCEPTION_PHONE_OR_OPENID_MISMATCH(-112, "手机号和微信授权标识不匹配"),
    EXCEPTION_PHONE_OR_SMS_CODE_EMPTY(-113, "手机号和验证码不能为空"),
    ADMIN_USER(-114, "管理员用户不能修改");


    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
