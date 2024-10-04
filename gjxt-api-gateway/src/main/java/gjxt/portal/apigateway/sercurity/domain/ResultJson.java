package gjxt.portal.apigateway.sercurity.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author goose
 * RESTful API 返回类型
 * Created at 2018/3/8.
 */
@Data
@ToString
public class ResultJson<T> implements Serializable{

    private static final long serialVersionUID = 783015033603078674L;
    private int code;
    private String msg;
    private T data;
    private boolean status;
    private T body;

    public static ResultJson ok() {
        return ok("");
    }

    public static ResultJson ok(Object o) {
        return new ResultJson(ResultCode.SUCCESS, o);
    }

    public static ResultJson failure(ResultCode code) {
        return new ResultJson(code);
    }
    public static ResultJson failure(String msg) {
        return new ResultJson(msg);
    }

    public static ResultJson failure(ResultCode code, Object o) {
        return new ResultJson(code, o);
    }

    public ResultJson (ResultCode resultCode) {
        setResultCode(resultCode);
    }

    public ResultJson (ResultCode resultCode,T data) {
        setResultCode(resultCode);
        this.data = data;
    }

    public void setResultCode(ResultCode resultCode) {
    	if(resultCode != null ) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
    	}
    }

    public ResultJson (String msg) {
        this.code = ResultCode.FAIL.getCode();
        this.msg = msg;
    }
}
