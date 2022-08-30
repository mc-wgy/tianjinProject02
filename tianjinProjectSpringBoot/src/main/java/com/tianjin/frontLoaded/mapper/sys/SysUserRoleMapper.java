package com.tianjin.frontLoaded.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianjin.frontLoaded.bean.model.sys.SysRole;
import com.tianjin.frontLoaded.bean.model.sys.SysUser;
import com.tianjin.frontLoaded.bean.model.sys.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    void deleteByUserList(List<SysUser> sysUserList);

    void deleteByRoleList(List<SysRole> sysRoleList);
}