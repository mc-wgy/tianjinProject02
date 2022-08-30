package com.tianjin.frontLoaded.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianjin.frontLoaded.bean.model.sys.SysMenu;
import com.tianjin.frontLoaded.bean.model.sys.SysRole;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysMenu> findRoleMenus(Long roleId);


}