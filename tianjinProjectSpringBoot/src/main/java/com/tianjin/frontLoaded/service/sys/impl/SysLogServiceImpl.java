package com.tianjin.frontLoaded.service.sys.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianjin.frontLoaded.bean.model.sys.SysLog;
import com.tianjin.frontLoaded.mapper.sys.SysLogMapper;
import com.tianjin.frontLoaded.service.sys.SysLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author RainRAY
 * @Date 2022/8/26
 * @Version 1.0
 */
@Service
@Transactional
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
}
