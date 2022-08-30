package com.tianjin.frontLoaded.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tianjin.frontLoaded.bean.dto.LoginRequestDto;
import com.tianjin.frontLoaded.bean.model.sys.SysLoginLog;
import com.tianjin.frontLoaded.bean.model.sys.SysUser;
import com.tianjin.frontLoaded.bean.request.JsonResponse;
import com.tianjin.frontLoaded.service.sys.SysLoginLogService;
import com.tianjin.frontLoaded.service.sys.SysUserService;
import com.tianjin.frontLoaded.util.IPUtil;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author RainRAY
 * @Date 2022/8/16
 * @Version 1.0
 */
@RestController
@RequestMapping()
public class AuthController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysLoginLogService sysLoginLogService;
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletRequest request;

    @PostMapping("/login")
    public JsonResponse<LoginRequestDto> login(@RequestBody SysUser loginUser) throws JSONException {
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        SysUser dbSysUser = sysUserService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username).eq(SysUser::getDelFlag, new Byte("0")));
        if (dbSysUser == null) {
            return JsonResponse.error("用户名不存在");
        }
        if (!BCrypt.checkpw(password, dbSysUser.getPassword())) {
            return JsonResponse.error("密码错误");
        }
        StpUtil.login(dbSysUser.getId());
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setUserName(username);
        sysLoginLog.setStatus("login");
        sysLoginLog.setIp(IPUtil.getIpAddress(request));
        sysLoginLog.saveData(username);
        sysLoginLogService.save(sysLoginLog);
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setAuthorities(sysUserService.getUserPermissions(dbSysUser.getId()));
        loginRequestDto.setAuthenticated(true);
        loginRequestDto.setSessionId(session.getId());
        loginRequestDto.setToken(StpUtil.getTokenValue());
        loginRequestDto.setName(dbSysUser.getUsername());
        return JsonResponse.ok(loginRequestDto);
    }

    @GetMapping("/logout")
    public JsonResponse<String> logout() {
        SysUser sysUser = sysUserService.getById(StpUtil.getLoginIdAsLong());
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setUserName(sysUser.getUsername());
        sysLoginLog.setStatus("logout");
        sysLoginLog.setIp(IPUtil.getIpAddress(request));
        sysLoginLog.saveData(sysUser.getUsername());
        sysLoginLogService.save(sysLoginLog);
        StpUtil.logout();
        return JsonResponse.ok();
    }

    @GetMapping("/captcha")
    public JsonResponse<Map<String,String>> captcha(){
        int width=200;//宽
        int height=40;//高
        int codeCount=4;//验证码位数
        int lineCount=1;//几行
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(width,height, codeCount, lineCount);
        captcha.setFont(new Font("Consolas", Font.BOLD, (int) ((double) height * 0.75D)));
        //captcha.setGenerator(new RandomGenerator(codeCount));//字符串验证码
        //captcha.setGenerator(new MathGenerator(codeCount));//算数验证码
        captcha.setGenerator(new RandomGenerator("123456789",codeCount));//数字验证码
        captcha.createCode();
        String verifyCode = captcha.getCode();
        String imageBase64 = "data:image/png;base64," + captcha.getImageBase64();
        Map<String,String> param=new HashMap<>();
        param.put("imageBase64",imageBase64);
        param.put("verifyCode",verifyCode);
        return JsonResponse.ok(param);
    }
}
