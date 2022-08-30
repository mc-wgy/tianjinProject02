package com.tianjin.frontLoaded.bean.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author RainRAY
 * @Date 2022/8/26
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseModel implements Serializable {
    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String lastUpdateBy;

    /**
     * 更新时间
     */
    private Date lastUpdateTime;

    public void saveData(String username){
        this.createBy=username;
        this.createTime=new Date();
        updateData(username);
    }

    public void updateData(String username){
        this.lastUpdateBy=username;
        this.lastUpdateTime=new Date();
    }
}
