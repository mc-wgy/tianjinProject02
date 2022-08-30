package com.tianjin.frontLoaded.handler;

import cn.dev33.satoken.exception.NotLoginException;
import com.tianjin.frontLoaded.bean.request.HttpCode;
import com.tianjin.frontLoaded.bean.request.JsonResponse;
import com.tianjin.frontLoaded.exception.LegendRpgWebException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author RainRAY
 * @Date 2022/8/24
 * @Version 1.0
 */
@Controller
@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {
    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public JsonResponse<Void> customException(NotLoginException e) {
        e.printStackTrace();
        return JsonResponse.error(HttpCode.UNAUTHORIZED);
    }
    /**
     * 服务器业务逻辑异常
     * @param e
     * @return
     */
    @ExceptionHandler(LegendRpgWebException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public JsonResponse<Void> unauthorizedExceptionHandler(LegendRpgWebException e){
        log.info("捕获到自定义异常 ex={}", e.getMessage(), e);
        e.printStackTrace();
        return JsonResponse.error(e.getHttpStatusCode(),e.getMessage());
    }
}
