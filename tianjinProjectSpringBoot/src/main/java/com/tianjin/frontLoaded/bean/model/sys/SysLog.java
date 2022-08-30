package com.tianjin.frontLoaded.bean.model.sys;

import com.tianjin.frontLoaded.bean.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sys_log
 * @author 
 */
@ApiModel(value="com.tianjin.frontLoaded.bean.model.SysLog系统操作日志")
@Data
public class SysLog extends BaseModel {
    /**
     * 编号
     */
    @ApiModelProperty(value="编号")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String userName;

    /**
     * 用户操作
     */
    @ApiModelProperty(value="用户操作")
    private String operation;

    /**
     * 请求方法
     */
    @ApiModelProperty(value="请求方法")
    private String method;

    /**
     * 请求参数
     */
    @ApiModelProperty(value="请求参数")
    private String params;

    /**
     * 执行时长(毫秒)
     */
    @ApiModelProperty(value="执行时长(毫秒)")
    private Long time;

    /**
     * IP地址
     */
    @ApiModelProperty(value="IP地址")
    private String ip;


    private static final long serialVersionUID = 1L;
}