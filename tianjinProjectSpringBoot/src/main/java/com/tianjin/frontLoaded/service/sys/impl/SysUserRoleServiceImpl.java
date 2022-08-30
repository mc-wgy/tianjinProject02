package com.tianjin.frontLoaded.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianjin.frontLoaded.bean.model.sys.SysRole;
import com.tianjin.frontLoaded.bean.model.sys.SysUser;
import com.tianjin.frontLoaded.bean.model.sys.SysUserRole;
import com.tianjin.frontLoaded.mapper.sys.SysUserRoleMapper;
import com.tianjin.frontLoaded.service.sys.SysUserRoleService;
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
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public void deleteByUserList(List<SysUser> sysUserList) {
        sysUserRoleMapper.deleteByUserList(sysUserList);
    }

    @Override
    public void deleteByRoleList(List<SysRole> sysRoleList) {
        sysUserRoleMapper.deleteByRoleList(sysRoleList);
    }
}
