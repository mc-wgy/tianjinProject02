package com.tianjin.frontLoaded.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author RainRAY
 * @Date 2022/8/24
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDetailsRequestDto {
    private String remoteAddress;

    private String sessionId;
}
