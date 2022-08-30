package com.tianjin.frontLoaded.bean.model.sys;

import java.io.Serializable;
import java.util.Date;

import com.tianjin.frontLoaded.bean.model.BaseModel;
import lombok.Data;

/**
 * sys_login_log
 * @author 
 */
@Data
public class SysLoginLog extends BaseModel {
    /**
     * 编号
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 登录状态（online:在线，登录初始状态，方便统计在线人数；login:退出登录后将online置为login；logout:退出登录）
     */
    private String status;

    /**
     * IP地址
     */
    private String ip;

    private static final long serialVersionUID = 1L;

}