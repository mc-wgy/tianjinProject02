package com.tianjin.frontLoaded.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tianjin.frontLoaded.bean.dto.ItemDto;
import com.tianjin.frontLoaded.bean.model.sys.SysLog;
import com.tianjin.frontLoaded.bean.model.sys.SysLoginLog;
import com.tianjin.frontLoaded.bean.request.JsonResponse;
import com.tianjin.frontLoaded.bean.request.PageInfo;
import com.tianjin.frontLoaded.bean.request.PageParam;
import com.tianjin.frontLoaded.service.sys.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author RainRAY
 * @Date 2022/8/29
 * @Version 1.0
 */
@RestController
@RequestMapping("/log")
@SaCheckLogin
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @PostMapping("/delete")
    public JsonResponse<String>delete(@RequestBody ItemDto<SysLoginLog> itemDto){
        sysLogService.removeByIds(itemDto.getParams());
        return JsonResponse.ok();
    }
    @PostMapping("/findPage")
    public JsonResponse<PageInfo<SysLog>>findPage(@RequestBody PageParam<SysLog> param){
        IPage<SysLog> page = sysLogService.page(param.buildPage(), new LambdaQueryWrapper<SysLog>().like(SysLog::getUserName, param.getParams().getUserName()).orderByDesc(SysLog::getCreateTime));
        return JsonResponse.ok(new PageInfo<SysLog>(page));
    }
}
