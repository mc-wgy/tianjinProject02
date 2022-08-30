package com.tianjin.frontLoaded.bean.model.sys;

import com.tianjin.frontLoaded.bean.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * sys_role_menu
 * @author 
 */
@ApiModel(value="com.tianjin.frontLoaded.bean.model.SysRoleMenu角色菜单")
@Data
public class SysRoleMenu extends BaseModel {
    /**
     * 编号
     */
    @ApiModelProperty(value="编号")
    private Long id;

    /**
     * 角色ID
     */
    @ApiModelProperty(value="角色ID")
    private Long roleId;

    /**
     * 菜单ID
     */
    @ApiModelProperty(value="菜单ID")
    private Long menuId;

    private static final long serialVersionUID = 1L;

}