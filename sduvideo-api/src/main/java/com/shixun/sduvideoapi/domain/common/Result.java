package com.shixun.sduvideoapi.domain.common;

import lombok.Data;

@Data
public class Result<T> {
    //返回码
    private String return_code;

    //提示信息
    private String tips;

    private T data;

    /**
     * 普通成功
     */
    public static Result success() {
        Result r = new Result();
        r.return_code = "success";
        r.tips = "操作成功";
        return r;
    }

    /**
     * 返回数据成功
     */
    public static <T> Result<T> success(T object) {
        Result<T> r = new Result<T>();
        r.data = object;
        r.return_code = "success";
        r.tips = "操作成功";
        return r;
    }

    /**
     * 错误
     */
    public static <T> Result<T> error(String msg) {
        Result r = new Result();
        r.data = msg;
        r.return_code = "error";
        r.tips = "操作失败";
        return r;
    }
}
