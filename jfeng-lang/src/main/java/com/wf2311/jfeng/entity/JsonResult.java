package com.wf2311.jfeng.entity;

import com.wf2311.jfeng.exception.CustomException;
import lombok.Builder;
import lombok.Data;

/**
 * @author wf2311
 * @time 2017/12/26 13:36.
 */
@Data
@Builder
public class JsonResult<T> {
    /**
     * 执行是否成功
     */
    private Boolean success;
    /**
     * 执行结果代码
     */
    private Integer code;
    /**
     * 执行结果信息
     */
    private String message;

    /**
     * 执行结果数据
     */
    private T data;

    public static <T> JsonResult<T> ok() {
        return JsonResult.<T>builder().success(true).build();
    }

    public static <T> JsonResult<T> ok(T data) {
        return JsonResult.<T>builder().success(true).data(data).build();
    }

    public static <T> JsonResult<T> ok(String message) {
        return JsonResult.<T>builder().success(true).message(message).build();
    }

    public static <T> JsonResult<T> error() {
        return JsonResult.<T>builder().success(false).build();
    }

    public static <T> JsonResult<T> error(String message) {
        return of(null, false, message, null);
    }

    public static <T> JsonResult<T> error(int code) {
        return of(code, false, null, null);
    }

    public static <T> JsonResult<T> of(Integer code, Boolean success, String message, T data) {
        return JsonResult.<T>builder().code(code).success(success).message(message).data(data).build();
    }

    public static <T> JsonResult<T> exception(CustomException exception){
        return of(exception.getCode(), false, exception.getMessage(), null);
    }

    public JsonResult success(boolean success) {
        this.success = success;
        return this;
    }

    public JsonResult code(int code) {
        this.code = code;
        return this;
    }

    public JsonResult message(String message) {
        this.message = message;
        return this;
    }

    public JsonResult data(T data) {
        this.data = data;
        return this;
    }
}
