package com.dexterlab.crm.core.model;

import java.io.Serializable;

public class Result  implements Serializable {

    /**
     * ajax响应编码，200 表示正常请求，不存在非授权或者未登录请求
     */
    public static final int CODE_DEF = 200;

    /**
     * 未登录，前端判断后，跳转到登录页面
     */
    public static final int CODE_1100 = 1100;

    /**
     * 无权访问
     */
    public static final int CODE_1403 = 1403;

    private Integer code = CODE_DEF;

    private String msg;

    private Object obj;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public static Result error(){
        Result r = new Result();
        r.setMsg("系统异常，请稍后重试");
        return r;
    }
    public static Result error(String msg){
        Result r = new Result();
        r.setMsg(msg == null ? "系统异常，请稍后重试" : msg);
        return r;
    }
    public static Result error(int code,String msg){
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg == null ? "系统异常，请稍后重试" : msg);
        return r;
    }
    public static Result ok() {
        Result r = new Result();
        return r;
    }
    public static Result ok(String msg) {
        Result r = new Result();
        r.setMsg(msg);
        return r;
    }
    public static Result ok(Object obj) {
        Result r = new Result();
        r.setObj(obj);
        return r;
    }
    public static Result ok(Object obj,String msg) {
        Result r = new Result();
        r.setObj(obj);
        r.setMsg(msg);
        return r;
    }

    public static Result of(Integer code,String msg, Object obj) {
        Result r = new Result();
        r.setCode(code);
        r.setObj(obj);
        r.setMsg(msg);
        return r;
    }
}