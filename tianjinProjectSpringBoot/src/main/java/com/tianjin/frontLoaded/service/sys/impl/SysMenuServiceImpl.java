package com.tianjin.frontLoaded.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianjin.frontLoaded.bean.model.sys.SysMenu;
import com.tianjin.frontLoaded.mapper.sys.SysMenuMapper;
import com.tianjin.frontLoaded.service.sys.SysMenuService;
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
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findMenuTree() {
        return sysMenuMapper.findMenuTree();
    }

    @Override
    public List<SysMenu> findNavTree() {
        return sysMenuMapper.findNavTree();
    }
}
