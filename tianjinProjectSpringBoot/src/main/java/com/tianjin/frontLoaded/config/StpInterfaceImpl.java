package com.tianjin.frontLoaded.config;

import cn.dev33.satoken.stp.StpInterface;
import com.tianjin.frontLoaded.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限注入实现类
 * @author RainRAY
 * @version 1.0
 * @date 2021/6/2 18:46
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<String> getPermissionList(Object o, String s) {
        return new ArrayList<>(sysUserService.getUserPermissions(Long.valueOf(String.valueOf(o))));
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return sysUserService.getUserRole(Long.valueOf(String.valueOf(o)));
    }
}
