package com.mjump.game.ws.util;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author
 * @date 2017/3/13
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    private int ret;

    private String msg;

    private String debug;

    private T model;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }
}
