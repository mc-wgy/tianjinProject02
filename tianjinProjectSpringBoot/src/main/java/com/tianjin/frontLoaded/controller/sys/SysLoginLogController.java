package com.tianjin.frontLoaded.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tianjin.frontLoaded.bean.dto.ItemDto;
import com.tianjin.frontLoaded.bean.model.sys.SysLoginLog;
import com.tianjin.frontLoaded.bean.request.JsonResponse;
import com.tianjin.frontLoaded.bean.request.PageInfo;
import com.tianjin.frontLoaded.bean.request.PageParam;
import com.tianjin.frontLoaded.service.sys.SysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author RainRAY
 * @Date 2022/8/29
 * @Version 1.0
 */
@RestController
@RequestMapping("/loginlog")
@SaCheckLogin
public class SysLoginLogController {

    @Autowired
    private SysLoginLogService sysLoginLogService;

    @PostMapping("/findPage")
    public JsonResponse<PageInfo<SysLoginLog>> findPage(@RequestBody PageParam<SysLoginLog> param) {
        if(param.getParams().getUserName()==null){
            param.getParams().setUserName("");
        }
        IPage<SysLoginLog> page = sysLoginLogService.page(param.buildPage(), new LambdaQueryWrapper<SysLoginLog>().like(SysLoginLog::getUserName, param.getParams().getUserName()).orderByDesc(SysLoginLog::getCreateTime));
        return JsonResponse.ok(new PageInfo<SysLoginLog>(page));
    }
    @PostMapping("/delete")
    public JsonResponse<String> delete(@RequestBody ItemDto<SysLoginLog> itemDto){
        sysLoginLogService.removeByIds(itemDto.getParams());
        return JsonResponse.ok();
    }
}
