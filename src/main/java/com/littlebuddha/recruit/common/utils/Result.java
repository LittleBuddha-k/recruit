package com.littlebuddha.recruit.common.utils;

/**
 * Json返回数据类
 */
public class Result<T> {

    private String code;
    private String msg;
    private T data;

    //只是迎合layui树形表格所加字段
    private Integer count;

    private boolean success;

    public Result() {
    }

    public Result(String msg, boolean success) {
        this.msg = msg;
        this.success = success;
    }

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(String code, String msg, T data, Integer count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
