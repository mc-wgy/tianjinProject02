package com.tianjin.frontLoaded.bean.model.sys;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.tianjin.frontLoaded.bean.model.BaseModel;
import lombok.Data;

/**
 * sys_user
 * @author 
 */
@Data
public class SysUser extends BaseModel {
    /**
     * 编号
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 加密盐
     */
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private Byte status;

    /**
     * 机构ID
     */
    private Long deptId;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    private Byte delFlag;

    @TableField(exist = false)
    private List<SysUserRole> userRoles;

    @TableField(exist = false)
    private String roleNames;

    private static final long serialVersionUID = 1L;

}