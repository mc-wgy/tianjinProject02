package com.tianjin.frontLoaded.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianjin.frontLoaded.bean.model.sys.SysMenu;
import com.tianjin.frontLoaded.bean.model.sys.SysRole;
import com.tianjin.frontLoaded.bean.model.sys.SysRoleMenu;

import java.util.List;

/**
 * @Author RainRAY
 * @Date 2022/8/25
 * @Version 1.0
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    void deleteByRoleList(List<SysRole> sysRoleList);

    void deleteByMenuList(List<SysMenu> sysMenuList);
}
