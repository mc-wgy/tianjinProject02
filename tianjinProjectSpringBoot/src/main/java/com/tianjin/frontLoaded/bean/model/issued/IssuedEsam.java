package com.tianjin.frontLoaded.bean.model.issued;

import com.tianjin.frontLoaded.bean.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * issued_esam
 * @author 
 */
@ApiModel(value="com.tianjin.frontLoaded.bean.model.IssuedEsamesam信息")
@Data
public class IssuedEsam extends BaseModel {
    private String id;

    /**
     * 信息ID
     */
    @ApiModelProperty(value="信息ID")
    private String infoId;

    /**
     * 审核ID
     */
    @ApiModelProperty(value="审核ID")
    private String waitId;

    /**
     * 发行编号
     */
    @ApiModelProperty(value="发行编号")
    private String cardNumber;

    /**
     * IC卡号
     */
    @ApiModelProperty(value="IC卡号")
    private String cardNo;

    /**
     * OBU序列号
     */
    @ApiModelProperty(value="OBU序列号")
    private String serialNo;

    /**
     * OBU物理号
     */
    @ApiModelProperty(value="OBU物理号")
    private String obuMac;

    /**
     * 发行状态
     */
    @ApiModelProperty(value="发行状态")
    private Integer issuedStatus;

    /**
     * 发行日期
     */
    @ApiModelProperty(value="发行日期")
    private Date issuedDate;


    /**
     * 发行人
     */
    @ApiModelProperty(value="发行人")
    private String userId;

    /**
     * IC卡序列号
     */
    @ApiModelProperty(value="IC卡序列号")
    private String esamUniqueNo;

    /**
     * obu密钥进度状态
     */
    @ApiModelProperty(value="obu密钥进度状态")
    private Integer keyCode;

    /**
     * ic密钥进度状态
     */
    @ApiModelProperty(value="ic密钥进度状态")
    private Integer icKeyCode;

    /**
     * 是否删除  -1：已删除  0：正常
     */
    @ApiModelProperty(value="是否删除  -1：已删除  0：正常")
    private Byte delFlag;

    private static final long serialVersionUID = 1L;
}