package com.wangpiece.ious.common;

import java.io.Serializable;

/**
 * @author wang.xu
 * @desc
 * @date 2018-12-25 21:28
 */
public class CommonResult<T> implements Serializable {
    private static final long serialVersionUID = -8424589501798882117L;

    /**
     * 信息
     */
    private String message;
    /**
     * 代码
     */
    private int code;

    /**
     * 是否成功true-成功 false-失败
     */
    private boolean successful;
    /**
     * 返回的数据
     */
    private T data;

    public CommonResult() {
    }

    public CommonResult(String message, int code, boolean successful, T data) {
        this.message = message;
        this.code = code;
        this.successful = successful;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
