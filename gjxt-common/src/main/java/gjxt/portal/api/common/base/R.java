package gjxt.portal.api.common.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;

/**
 *
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    public static final String STATUS = "status";
    public static final String DATA = "data";
    public static final String CODE = "code";
    public static final String MSG = "msg";

    public R() {
        put(CODE, ResultCode.OK.getCode());
        put(MSG, "success");
    }

    public static R ok() {
        R r = new R();
        r.put(STATUS, true);
        return r;
    }

    public static R ok(Object data) {
        R r = new R();
        r.put(STATUS, true);
        r.put(DATA, data);
        return r;
    }

    public static R error() {
        R r = new R();
        r.put(CODE, ResultCode.ERROR.getCode());
        r.put(STATUS, false);
        return r;
    }

    public static R error(String message) {
        R r = new R();
        r.put(CODE, ResultCode.ERROR.getCode());
        r.put(STATUS, false);
        r.put(MSG, message);
        return r;
    }

    public static R error(IResultCode resultCode) {
        R r = new R();
        r.put(CODE, resultCode.getCode());
        r.put(MSG, resultCode.getMessage());
        r.put(STATUS, false);
        return r;
    }

    public static R error(Integer code, String message) {
        R r = new R();
        r.put(CODE, code);
        r.put(MSG, message);
        r.put(STATUS, false);
        return r;
    }

    public static R error(IResultCode resultCode, Object data) {
        R r = new R();
        r.put(CODE, resultCode.getCode());
        r.put(STATUS, false);
        r.put(DATA, data);
        return r;
    }

    public static R error(Integer code, String message, Object data) {
        R r = new R();
        r.put(CODE, code);
        r.put(STATUS, false);
        r.put(DATA, data);
        r.put(MSG, message);
        return r;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
