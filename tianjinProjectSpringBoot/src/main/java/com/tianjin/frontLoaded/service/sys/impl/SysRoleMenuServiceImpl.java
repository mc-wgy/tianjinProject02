package com.tianjin.frontLoaded.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianjin.frontLoaded.bean.model.sys.SysMenu;
import com.tianjin.frontLoaded.bean.model.sys.SysRole;
import com.tianjin.frontLoaded.bean.model.sys.SysRoleMenu;
import com.tianjin.frontLoaded.mapper.sys.SysRoleMenuMapper;
import com.tianjin.frontLoaded.service.sys.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author RainRAY
 * @Date 2022/8/25
 * @Version 1.0
 */
@Service
@Transactional
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public void deleteByRoleList(List<SysRole> sysRoleList) {
        sysRoleMenuMapper.deleteByRoleList(sysRoleList);
    }

    @Override
    public void deleteByMenuList(List<SysMenu> sysMenuList) {
        sysRoleMenuMapper.deleteByMenuList(sysMenuList);
    }
}
