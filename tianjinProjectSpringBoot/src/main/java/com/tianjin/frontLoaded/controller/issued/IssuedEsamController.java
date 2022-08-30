package com.tianjin.frontLoaded.controller.issued;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tianjin.frontLoaded.bean.model.issued.IssuedEsam;
import com.tianjin.frontLoaded.bean.request.JsonResponse;
import com.tianjin.frontLoaded.bean.request.PageInfo;
import com.tianjin.frontLoaded.bean.request.PageParam;
import com.tianjin.frontLoaded.service.issued.IssuedEsamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author RainRAY
 * @Date 2022/8/29
 * @Version 1.0
 */

@RestController
@RequestMapping("/issuedEsam")
@SaCheckLogin
public class IssuedEsamController {

    @Autowired
    private IssuedEsamService issuedEsamService;

    @PostMapping("/findPage")
    public JsonResponse<PageInfo<IssuedEsam>> findPage(@RequestBody PageParam<IssuedEsam> param){
        IssuedEsam issuedEsam = param.getParams();
        if(StrUtil.isEmptyIfStr(issuedEsam.getSerialNo())){
            issuedEsam.setSerialNo("");
        }
        IPage<IssuedEsam> page = issuedEsamService.page(param.buildPage(), new LambdaQueryWrapper<IssuedEsam>().eq(IssuedEsam::getDelFlag, new Byte("0")).like(IssuedEsam::getSerialNo, issuedEsam.getSerialNo()));
        return JsonResponse.ok(new PageInfo<IssuedEsam>(page));
    }
}
