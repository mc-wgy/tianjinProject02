package com.tianjin.frontLoaded.bean.model.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.tianjin.frontLoaded.bean.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import lombok.Data;

/**
 * sys_menu
 * @author 
 */
@ApiModel(value="com.tianjin.frontLoaded.bean.model.SysMenu菜单管理")
@Data
public class SysMenu extends BaseModel {
    /**
     * 编号
     */
    @ApiModelProperty(value="编号")
    private Long id;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value="菜单名称")
    private String name;

    /**
     * 父菜单ID，一级菜单为0
     */
    @ApiModelProperty(value="父菜单ID，一级菜单为0")
    private Long parentId;

    /**
     * 菜单URL,类型：1.普通页面（如用户管理， /sys/user） 2.嵌套完整外部页面，以http(s)开头的链接 3.嵌套服务器页面，使用iframe:前缀+目标URL(如SQL监控， iframe:/druid/login.html, iframe:前缀会替换成服务器地址)
     */
    @ApiModelProperty(value="菜单URL,类型：1.普通页面（如用户管理， /sys/user） 2.嵌套完整外部页面，以http(s)开头的链接 3.嵌套服务器页面，使用iframe:前缀+目标URL(如SQL监控， iframe:/druid/login.html, iframe:前缀会替换成服务器地址)")
    private String url;

    /**
     * 授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)
     */
    @ApiModelProperty(value="授权(多个用逗号分隔，如：sys:user:add,sys:user:edit)")
    private String perms;

    /**
     * 类型   0：目录   1：菜单   2：按钮
     */
    @ApiModelProperty(value="类型   0：目录   1：菜单   2：按钮")
    private Integer type;

    /**
     * 菜单图标
     */
    @ApiModelProperty(value="菜单图标")
    private String icon;

    /**
     * 排序
     */
    @ApiModelProperty(value="排序")
    private Integer orderNum;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    @ApiModelProperty(value="是否删除  -1：已删除  0：正常")
    private Byte delFlag;

    private Integer level;

    @TableField(exist = false)
    private List<SysMenu> children;

    private static final long serialVersionUID = 1L;

}