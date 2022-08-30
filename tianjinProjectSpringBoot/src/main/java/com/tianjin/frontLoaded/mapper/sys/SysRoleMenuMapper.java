package com.tianjin.frontLoaded.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianjin.frontLoaded.bean.model.sys.SysMenu;
import com.tianjin.frontLoaded.bean.model.sys.SysRole;
import com.tianjin.frontLoaded.bean.model.sys.SysRoleMenu;

import java.util.List;

/**
 * @Author RainRAY
 * @Date 2022/8/25
 * @Version 1.0
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    void deleteByRoleList(List<SysRole> sysRoleList);

    void deleteByMenuList(List<SysMenu> sysMenuList);
}
