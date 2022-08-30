package com.tianjin.frontLoaded.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianjin.frontLoaded.bean.model.sys.SysMenu;
import com.tianjin.frontLoaded.bean.model.sys.SysRole;

import java.util.List;

/**
 * @Author RainRAY
 * @Date 2022/8/25
 * @Version 1.0
 */
public interface SysRoleService extends IService<SysRole> {
    List<SysMenu> findRoleMenus(SysRole sysRole);

}
