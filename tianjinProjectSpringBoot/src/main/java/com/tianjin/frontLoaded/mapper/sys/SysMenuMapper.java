package com.tianjin.frontLoaded.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tianjin.frontLoaded.bean.model.sys.SysMenu;

import java.util.List;

public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> getChildrenByParentId(Long parentId);

    List<SysMenu> getNevChildrenByParentId(Long parentId);

    List<SysMenu> findMenuTree();

    List<SysMenu> findNavTree();
}