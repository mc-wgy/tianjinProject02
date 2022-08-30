package com.tianjin.frontLoaded.bean.model.sys;

import com.tianjin.frontLoaded.bean.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sys_user_role
 * @author 
 */
@ApiModel(value="com.tianjin.frontLoaded.bean.model.SysUserRole用户角色")
@Data
public class SysUserRole extends BaseModel {
    /**
     * 编号
     */
    @ApiModelProperty(value="编号")
    private Long id;

    /**
     * 用户ID
     */
    @ApiModelProperty(value="用户ID")
    private Long userId;

    /**
     * 角色ID
     */
    @ApiModelProperty(value="角色ID")
    private Long roleId;

    private static final long serialVersionUID = 1L;

}