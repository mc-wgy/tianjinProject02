package com.tianjin.frontLoaded.bean.model.sys;

import com.tianjin.frontLoaded.bean.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sys_role
 * @author 
 */
@ApiModel(value="com.tianjin.frontLoaded.bean.model.SysRole角色管理")
@Data
public class SysRole extends BaseModel {
    /**
     * 编号
     */
    @ApiModelProperty(value="编号")
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value="角色名称")
    private String name;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    @ApiModelProperty(value="是否删除  -1：已删除  0：正常")
    private Byte delFlag;

    private static final long serialVersionUID = 1L;

}