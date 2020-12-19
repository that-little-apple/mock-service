package com.jd.jshop.mockservice.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 **/
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private boolean success = true;
    private Integer code;
    private Integer msgLevel;
    private String msg;
    private T data;

    public Boolean isSuccess() {
        return this.success;
    }

    public boolean isFail() {
        return !isSuccess();
    }

    public void fail(String msg) {
        this.fail(msg, null, null);
    }

    public void fail(String msg, Integer code) {
        this.fail(msg, code, null);
    }

    public void fail(String msg, T data) {
        this.fail(msg, null, data);
    }

    public void fail(String msg, Integer code, T data) {
        this.success = Boolean.FALSE;
        this.setMsg(msg);
        this.setCode(code);
        this.data = data;
    }


    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getMsgLevel() {
        return msgLevel;
    }

    public void setMsgLevel(Integer msgLevel) {
        this.msgLevel = msgLevel;
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
