package com.tianjin.frontLoaded.aspect;

import cn.dev33.satoken.stp.StpUtil;
import com.tianjin.frontLoaded.bean.model.sys.SysLog;
import com.tianjin.frontLoaded.service.sys.SysLogService;
import com.tianjin.frontLoaded.service.sys.SysUserService;
import com.tianjin.frontLoaded.util.IPUtil;
import net.minidev.json.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author RainRAY
 * @Date 2022/8/24
 * @Version 1.0
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private HttpServletRequest request;

    @Around("@annotation(aspectLog)")
    public Object around(ProceedingJoinPoint joinPoint, AspectLog aspectLog) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long time = System.currentTimeMillis() - beginTime;
        String username = sysUserService.getUsernameById(StpUtil.getLoginIdAsLong());
        SysLog sysLog = new SysLog();
        sysLog.setUserName(username);
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        sysLog.setMethod(className + "." + methodName + "()");

        Object[] args = joinPoint.getArgs();
        try {
            String params = JSONObject.toJSONString((Map<String, ? extends Object>) args[0]);
            if (params.length() > 200) {
                params = params.substring(0, 200) + "...";
            }
            sysLog.setParams(params);
        } catch (Exception ignored) {
        }

        // 设置IP地址
        sysLog.setIp(IPUtil.getIpAddress(request));
        // 执行时长(毫秒)
        sysLog.setTime(time);
        sysLog.saveData(username);
        sysLogService.save(sysLog);
        return proceed;
    }
}
