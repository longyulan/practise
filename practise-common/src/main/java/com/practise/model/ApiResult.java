package com.practise.model;

import lombok.Data;

/**
 * 统一返回
 * @author longyulan
 * @date 2023/6/15
 */
@Data
public class ApiResult<T> {
    /**
     * 成功或失败
     */
    private Boolean success;
    /**
     * 消息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public static <T> ApiResult<T> ok() {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(true);
        return result;
    }

    public static <T> ApiResult<T> ok(T data) {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> ApiResult<T> fail() {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(false);
        return result;
    }

    public static <T> ApiResult<T> fail(String message) {
        ApiResult<T> result = new ApiResult<>();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }
}
