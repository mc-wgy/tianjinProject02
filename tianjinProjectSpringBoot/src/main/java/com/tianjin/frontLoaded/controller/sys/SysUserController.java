package com.tianjin.frontLoaded.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tianjin.frontLoaded.aspect.AspectLog;
import com.tianjin.frontLoaded.bean.dto.ItemDto;
import com.tianjin.frontLoaded.bean.model.sys.SysUser;
import com.tianjin.frontLoaded.bean.model.sys.SysUserRole;
import com.tianjin.frontLoaded.bean.request.JsonResponse;
import com.tianjin.frontLoaded.bean.request.PageInfo;
import com.tianjin.frontLoaded.bean.request.PageParam;
import com.tianjin.frontLoaded.service.sys.SysUserRoleService;
import com.tianjin.frontLoaded.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author RainRAY
 * @Date 2022/8/24
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
@SaCheckLogin
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @AspectLog
    @PostMapping("/save")
    public JsonResponse<String> save(@RequestBody SysUser sysUser) {
        String username = sysUserService.getUsernameById(StpUtil.getLoginIdAsLong());
        if (sysUser.getId() == null || sysUser.getId() == 0) {
            sysUser.setPassword(BCrypt.hashpw(sysUser.getPassword()));
            sysUser.saveData(username);
            sysUserService.save(sysUser);
        } else {
            sysUser.setPassword(null);
            List<SysUserRole> sysUserRoleList = sysUser.getUserRoles().stream()
                    .filter(sysUserRole -> sysUserRole.getRoleId() != null)
                    .peek(sysUserRole -> {
                        sysUserRole.saveData(username);
                    }).collect(Collectors.toList());
            sysUserRoleService.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, sysUser.getId()));
            sysUserRoleService.saveBatch(sysUserRoleList);
            sysUser.updateData(username);
            sysUserService.updateById(sysUser);
        }
        return JsonResponse.ok();
    }

    @AspectLog
    @PostMapping("/delete")
    public JsonResponse<String> delete(@RequestBody ItemDto<SysUser> ids) {
        List<SysUser> sysUserList = ids.getParams().stream().filter(sysUser -> sysUser.getId() != null).peek(sysUser -> {
            sysUser.setDelFlag(new Byte("1"));
        }).collect(Collectors.toList());
        sysUserService.updateBatchById(sysUserList);
        sysUserRoleService.deleteByUserList(sysUserList);
        return JsonResponse.ok();
    }

    @AspectLog
    @PostMapping("/findPage")
    public JsonResponse<PageInfo<SysUser>> findPage(@RequestBody PageParam<SysUser> param) {
        IPage<SysUser> page = sysUserService.page(param.buildPage(), new LambdaQueryWrapper<SysUser>().like(SysUser::getUsername, param.getParams().getUsername()).eq(SysUser::getDelFlag, new Byte("0")));
        List<SysUser> records = page.getRecords().stream().peek(sysUser -> {
            sysUser.setUserRoles(sysUserRoleService.list(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, sysUser.getId())));
            String string = sysUserService.getUserRoleRemarks(sysUser.getId()).toString();
            string = string.substring(1, string.length() - 1);
            sysUser.setRoleNames(string);
        }).collect(Collectors.toList());
        page.setRecords(records);
        return JsonResponse.ok(new PageInfo<>(page));
    }

    @AspectLog
    @GetMapping("/findPermissions")
    public JsonResponse<List<String>> findPermissions() {
        List<String> userPermissions = sysUserService.getUserPermissions(StpUtil.getLoginIdAsLong());
        return JsonResponse.ok(userPermissions);
    }

    @AspectLog
    @GetMapping("/findByName")
    public JsonResponse<SysUser> findByName(@RequestParam String name) {
        SysUser sysUser = sysUserService.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, name).eq(SysUser::getDelFlag, new Byte("0")));
        return JsonResponse.ok(sysUser);
    }

    @AspectLog
    @GetMapping("/updatePassword")
    public JsonResponse<String> updatePassword(String password, String newPassword) {
        SysUser sysUser = sysUserService.getById(StpUtil.getLoginIdAsLong());
        if (!BCrypt.checkpw(password, sysUser.getPassword())) {
            return JsonResponse.error("原密码错误");
        }
        sysUser.setPassword(BCrypt.hashpw(newPassword));
        sysUser.updateData(sysUser.getUsername());
        sysUserService.updateById(sysUser);
        return JsonResponse.ok();
    }

    @AspectLog
    @PostMapping("/exportUserExcelFile")
    public JsonResponse<String> exportUserExcelFile(@RequestBody List<SysUser> sysUserList) {
        File file = sysUserService.createUserExcelFile(sysUserList);
        return JsonResponse.ok();
    }
}
