package com.tianjin.frontLoaded.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tianjin.frontLoaded.bean.model.sys.SysMenu;

import java.util.List;

/**
 * @Author RainRAY
 * @Date 2022/8/25
 * @Version 1.0
 */
public interface SysMenuService extends IService<SysMenu> {
    List<SysMenu> findMenuTree();

    List<SysMenu> findNavTree();
}
