package com.tianjin.frontLoaded.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianjin.frontLoaded.bean.model.sys.SysLoginLog;
import com.tianjin.frontLoaded.mapper.sys.SysLoginLogMapper;
import com.tianjin.frontLoaded.service.sys.SysLoginLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author RainRAY
 * @Date 2022/8/24
 * @Version 1.0
 */
@Service
@Transactional
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {
}
