package com.littlebuddha.recruit.common.utils;

/**
 * Json返回数据类
 */
public class Result<T> {

    private String code;
    private String msg;
    private T data;

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
