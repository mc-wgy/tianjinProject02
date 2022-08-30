package com.tianjin.frontLoaded.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianjin.frontLoaded.bean.model.sys.SysRole;
import com.tianjin.frontLoaded.bean.model.sys.SysUser;

import java.util.List;

public interface SysUserMapper extends BaseMapper<SysUser> {
    List<String> getUserPermissions(Long userId);

    List<String> getUserRole(Long userId);

    List<SysRole> findUserRoles(Long userId);

    List<String> getUserRoleRemarks(Long userId);

    String getUsernameById(long userId);
}