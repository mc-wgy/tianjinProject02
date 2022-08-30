package com.tianjin.frontLoaded.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tianjin.frontLoaded.bean.dto.ItemDto;
import com.tianjin.frontLoaded.bean.model.sys.SysMenu;
import com.tianjin.frontLoaded.bean.model.sys.SysRole;
import com.tianjin.frontLoaded.bean.model.sys.SysRoleMenu;
import com.tianjin.frontLoaded.bean.request.JsonResponse;
import com.tianjin.frontLoaded.bean.request.PageInfo;
import com.tianjin.frontLoaded.bean.request.PageParam;
import com.tianjin.frontLoaded.service.sys.SysRoleMenuService;
import com.tianjin.frontLoaded.service.sys.SysRoleService;
import com.tianjin.frontLoaded.service.sys.SysUserRoleService;
import com.tianjin.frontLoaded.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author RainRAY
 * @Date 2022/8/25
 * @Version 1.0
 */
@RestController
@RequestMapping("/role")
@SaCheckLogin
public class SysRoleController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @PostMapping("/save")
    public JsonResponse<String> save(@RequestBody SysRole sysRole){
        String username = sysUserService.getUsernameById(StpUtil.getLoginIdAsLong());
        if(sysRole.getId()==null||sysRole.getId()==0) {
            sysRole.saveData(username);
            sysRoleService.save(sysRole);
        }else{
            sysRole.updateData(username);
            sysRoleService.updateById(sysRole);
        }
        return JsonResponse.ok();
    }

    @PostMapping("/delete")
    public JsonResponse<String> delete(@RequestBody ItemDto<SysRole> itemDto){
        List<SysRole> sysRoleList = itemDto.getParams().stream().filter(sysRole -> sysRole.getId() != null).peek(sysRole -> {
            sysRole.setDelFlag(new Byte("1"));
            sysRole.updateData(sysUserService.getUsernameById(StpUtil.getLoginIdAsLong()));
        }).collect(Collectors.toList());
        sysRoleService.updateBatchById(sysRoleList);
        sysUserRoleService.deleteByRoleList(sysRoleList);
        sysRoleMenuService.deleteByRoleList(sysRoleList);
        return JsonResponse.ok();
    }
    @PostMapping("/findPage")
    public JsonResponse<PageInfo<SysRole>> findPage(@RequestBody PageParam<SysRole> param){
        IPage<SysRole> page = sysRoleService.page(param.buildPage(), new LambdaQueryWrapper<SysRole>().like(SysRole::getName, param.getParams().getName()).eq(SysRole::getDelFlag,new Byte("0")));
        return JsonResponse.ok(new PageInfo<SysRole>(page));
    }
    @GetMapping("/findAll")
    public JsonResponse<List<SysRole>> findAll(){
        return JsonResponse.ok(sysRoleService.list(new LambdaQueryWrapper<SysRole>().eq(SysRole::getDelFlag,new Byte("0"))));
    }
    @GetMapping("/findRoleMenus")
    public JsonResponse<List<SysMenu>> findRoleMenus(SysRole sysRole){
        List<SysMenu> sysMenuList=sysRoleService.findRoleMenus(sysRole);
        return JsonResponse.ok(sysMenuList);
    }

    @PostMapping("/saveRoleMenus")
    public JsonResponse<String> saveRoleMenus(@RequestBody ItemDto<SysRoleMenu> itemDto){
        String username = sysUserService.getUsernameById(StpUtil.getLoginIdAsLong());
        List<SysRoleMenu> params = itemDto.getParams();
        List<SysRoleMenu> collect = params.stream().peek(sysRoleMenu -> {
            sysRoleMenu.saveData(username);
        }).collect(Collectors.toList());
        sysRoleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId,params.get(0).getRoleId()));
        sysRoleMenuService.saveBatch(collect);
        return JsonResponse.ok();
    }
}
