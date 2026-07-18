package com.flowsync.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一响应封装。
 *
 * 用法：
 *   Result.success().data("data", list)             → { code:200, msg:"操作成功", data:{ data:[...] } }
 *   Result.success().msg("新增成功")                  → { code:200, msg:"新增成功", data:{} }
 *   Result.error().msg("用户名或密码错误")             → { code:500, msg:"...", data:{} }
 */
public class Result {

    private Integer code;
    private String msg;
    private Map<String, Object> data = new HashMap<>();

    private Result() {}

    public static Result success() {
        Result r = new Result();
        r.code = 200;
        r.msg = "操作成功";
        return r;
    }

    public static Result error() {
        Result r = new Result();
        r.code = 500;
        r.msg = "操作失败";
        return r;
    }

    public Result data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public Result msg(String msg) {
        this.msg = msg;
        return this;
    }

    /** @deprecated 用 {@link #msg(String)} 代替；保留仅为兼容旧调用风格 */
    @Deprecated
    public Result Code(Integer code) {
        this.code = code;
        return this;
    }

    // ---------- getters & setters ----------

    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code; }

    public String getMsg() { return msg; }
    public void setMsg(String msg) { this.msg = msg; }

    public Map<String, Object> getData() { return data; }
    public void setData(Map<String, Object> data) { this.data = data; }
}
