package cn.liangqinghai.study.mbp.utils;

import java.util.HashMap;

/**
 * @author Mr.Liang
 * @date 2020/6/7
 */
public class Result extends HashMap<String, Object> {

    private static final long serialVersionUID = 8148664123119019866L;

    public Result() {
        put("code", 0);
    }


    public static Result error(int code, String msg) {

        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);

        return r;

    }

    public static Result error(String msg) {
        return error(500, msg);
    }

    public static Result error() {
        return error("未知错误.");
    }

    public static Result ok() {
        return new Result();
    }

    public static Result ok(String msg) {

        Result r = ok();
        r.put("msg", msg);

        return r;

    }

    public static Result ok(HashMap<String, Object> map) {

        Result r = ok();
        r.putAll(map);

        return r;

    }

    public Result put(String k, Object v) {

        super.put(k, v);

        return this;

    }
}
