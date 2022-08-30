package com.tianjin.frontLoaded.bean.request;

/**
 * 定义了返回的状态类型
 * @author RainRAY
 * @version 1.0
 * @date 2021/8/22 20:12
 */
public enum  HttpCode {

    /**
     * 客户端看到401状态码时，应该重新登陆
     */
    UNAUTHORIZED(401, "未授权"),


    USERNAME_EXISTS(402,"用户名已存在"),
    /**
     * 找不到数据
     */
    NULL(4000, "数据不存在!"),

    /**
     * 无权限操作!
     */
    NO_PERMISSION(40001,"无权限操作!"),
    ;

    private final int value;

    private final String msg;


    HttpCode(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }


    /**
     * Return the integer value of this status code.-
     */
    public int value() {
        return this.value;
    }

    /**
     * Return the msg of this status code.
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Return a string representation of this status code.
     */
    @Override
    public String toString() {
        return this.value + " " + name();
    }


    public static HttpCode valueOf(int statusCode) {
        HttpCode status = resolve(statusCode);
        if (status == null) {
            throw new IllegalArgumentException("没有找到该Http状态码包含状态 [" + statusCode + "]");
        }
        return status;
    }

    public static HttpCode resolve(int statusCode) {
        for (HttpCode status : values()) {
            if (status.value == statusCode) {
                return status;
            }
        }
        return null;
    }



}
