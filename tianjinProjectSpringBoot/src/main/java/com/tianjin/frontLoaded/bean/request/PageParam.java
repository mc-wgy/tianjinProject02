package com.tianjin.frontLoaded.bean.request;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author RainRAY
 * @version 1.0
 * @date 2021/8/23 11:20
 */
@Data
public class PageParam<T> {

    private Integer pageSize;

    private Integer pageNum;

    private T params;

    public Page<T> buildPage(){
        return new Page<>(this.pageNum,this.pageSize);
    }



}
