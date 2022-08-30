package com.tianjin.frontLoaded.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tianjin.frontLoaded.bean.dto.ItemDto;
import com.tianjin.frontLoaded.bean.model.sys.SysMenu;
import com.tianjin.frontLoaded.bean.request.JsonResponse;
import com.tianjin.frontLoaded.service.sys.SysMenuService;
import com.tianjin.frontLoaded.service.sys.SysRoleMenuService;
import com.tianjin.frontLoaded.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author RainRAY
 * @Date 2022/8/26
 * @Version 1.0
 */
@RestController
@RequestMapping("/menu")
@SaCheckLogin
public class SysMenuController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @PostMapping("/save")
    public JsonResponse<String> save(@RequestBody SysMenu sysMenu) {
        String username = sysUserService.getUsernameById(StpUtil.getLoginIdAsLong());
        SysMenu parent = sysMenuService.getOne(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getId, sysMenu.getParentId()));
        if (parent == null) {
            sysMenu.setLevel(0);
        } else {
            sysMenu.setLevel(parent.getLevel() + 1);
        }
        if (sysMenu.getId() == null || sysMenu.getId() == 0) {
            sysMenu.saveData(username);
            sysMenuService.save(sysMenu);
        } else {
            sysMenu.updateData(username);
            sysMenuService.updateById(sysMenu);
        }
        return JsonResponse.ok();
    }

    @PostMapping("/delete")
    public JsonResponse<String> delete(@RequestBody ItemDto<SysMenu> itemDto) {
        String username = sysUserService.getUsernameById(StpUtil.getLoginIdAsLong());
        List<SysMenu> sysMenuList = itemDto.getParams().stream().filter(sysMenu -> sysMenu.getId() != null).peek(sysMenu -> {
            sysMenu.setDelFlag(new Byte("1"));
            sysMenu.updateData(username);
        }).collect(Collectors.toList());
        sysMenuService.updateBatchById(sysMenuList);
        sysRoleMenuService.deleteByMenuList(sysMenuList);
        return JsonResponse.ok();
    }

    @GetMapping("/findMenuTree")
    public JsonResponse<List<SysMenu>> findMenuTree() {
        List<SysMenu> sysMenuList = sysMenuService.findMenuTree();
        return JsonResponse.ok(sysMenuList);
    }

    @GetMapping("/findNavTree")
    public JsonResponse<List<SysMenu>> findNavTree() {
        List<SysMenu> navTree = sysMenuService.findNavTree();
        return JsonResponse.ok(navTree);
    }
}
