package com.tianjin.frontLoaded.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianjin.frontLoaded.bean.model.sys.SysRole;
import com.tianjin.frontLoaded.bean.model.sys.SysUser;
import com.tianjin.frontLoaded.bean.model.sys.SysUserRole;

import java.util.List;

/**
 * @Author RainRAY
 * @Date 2022/8/25
 * @Version 1.0
 */
public interface SysUserRoleService extends IService<SysUserRole> {
    void deleteByUserList(List<SysUser> sysUserList);

    void deleteByRoleList(List<SysRole> sysRoleList);
}
