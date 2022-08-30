package com.tianjin.frontLoaded.bean.request;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author RainRAY
 * @version 1.0
 * @date 2021/8/22 20:05
 */

public class JsonResponse<T> {

    public static final int SUCCESS_STATUS = 200;

    public static final int DEFAULT_FAIL_STATUS = 400;

    public String message;

    @ApiModelProperty(value = "响应返回数据，根据每次的返回数据不同而不同")
    public T data;

    @ApiModelProperty(value = "状态: 200-成功")
    public Integer code;

    public JsonResponse(Integer code,T data) {
        this.data = data;
        this.code = code;
    }

    public JsonResponse(Integer code, T data, String message) {
        this.message = message;
        this.data = data;
        this.code = code;
    }

    public JsonResponse() {
    }



    public static <T> JsonResponse<T> ok(){
        return new JsonResponse<>(SUCCESS_STATUS,null,null);
    }

    public static <T> JsonResponse<T> ok(T data){
        return new JsonResponse<>(SUCCESS_STATUS,data,null);
    }

    public static <T> JsonResponse<T> error(String msg){
        return new JsonResponse<>(DEFAULT_FAIL_STATUS,null,msg);
    }

    public static <T> JsonResponse<T> error(Integer status, String msg){
        return new JsonResponse<>(status,null,msg);
    }

    public static <T> JsonResponse<T> error(HttpCode httpCode){
        return new JsonResponse<>(httpCode.value(),null,httpCode.getMsg());
    }



}
