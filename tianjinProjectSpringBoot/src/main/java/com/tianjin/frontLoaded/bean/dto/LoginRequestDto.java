package com.tianjin.frontLoaded.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author RainRAY
 * @Date 2022/8/24
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    private List<String> authorities;

    private LoginDetailsRequestDto loginDetailsRequestDto =new LoginDetailsRequestDto();

    private boolean authenticated;

    private String principal;

    private String credentials;

    private String token;

    private String name;

    public void setSessionId(String sessionId){
        this.loginDetailsRequestDto.setSessionId(sessionId);
    }
    public void setRemoteAddress(String remoteAddress){
        this.loginDetailsRequestDto.setSessionId(remoteAddress);
    }
}
