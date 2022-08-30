package com.tianjin.frontLoaded.service.issued.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tianjin.frontLoaded.bean.model.issued.IssuedEsam;
import com.tianjin.frontLoaded.mapper.issued.IssuedEsamMapper;
import com.tianjin.frontLoaded.service.issued.IssuedEsamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author RainRAY
 * @Date 2022/8/29
 * @Version 1.0
 */
@Service
@Transactional
public class IssuedEsamServiceImpl extends ServiceImpl<IssuedEsamMapper, IssuedEsam> implements IssuedEsamService {
}
