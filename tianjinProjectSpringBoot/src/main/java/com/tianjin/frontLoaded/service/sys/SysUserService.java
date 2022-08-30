package com.tianjin.frontLoaded.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianjin.frontLoaded.bean.model.sys.SysRole;
import com.tianjin.frontLoaded.bean.model.sys.SysUser;

import java.io.File;
import java.util.List;

/**
 * @Author RainRAY
 * @Date 2022/8/24
 * @Version 1.0
 */
public interface SysUserService extends IService<SysUser> {
    List<String> getUserPermissions(Long userId);

    List<String> getUserRole(Long userId);

    List<String> getUserRoleRemarks(Long userId);

    File createUserExcelFile(List<SysUser> sysUserList);

    List<SysRole> findUserRoles(Long id);

    String getUsernameById(long userId);
}
