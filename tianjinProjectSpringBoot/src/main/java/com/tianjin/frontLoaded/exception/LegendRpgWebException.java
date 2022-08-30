package com.tianjin.frontLoaded.exception;

import com.tianjin.frontLoaded.bean.request.HttpCode;
import org.springframework.http.HttpStatus;

/**
 * @author huangzy
 * @Description:
 * @date 2020/6/4 10:46
 */
public class LegendRpgWebException extends RuntimeException {


    /**
     *
     */
    private static final long serialVersionUID = -4137688758944857209L;

    /**
     * http状态码
     */
    private Integer httpStatusCode;


    /**
     * @param httpCode http状态码
     */
    public LegendRpgWebException(HttpCode httpCode) {
        super(httpCode.getMsg());
        this.httpStatusCode = httpCode.value();
    }


    public LegendRpgWebException(String msg) {
        super(msg);
        this.httpStatusCode = HttpStatus.BAD_REQUEST.value();
    }


    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }
}
