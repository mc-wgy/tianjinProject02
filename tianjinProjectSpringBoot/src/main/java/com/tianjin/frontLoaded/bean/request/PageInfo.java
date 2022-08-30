package com.tianjin.frontLoaded.bean.request;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

/**
 * @Author RainRAY
 * @Date 2022/8/25
 * @Version 1.0
 */
@Data
public class PageInfo<T> {
    private Long pageNum;
    private Long pageSize;
    private Long totalSize;
    private Long totalPages;
    private List<T> content;

    public PageInfo(IPage<T> iPage){
        this.pageNum=iPage.getCurrent();
        this.pageSize=iPage.getSize();
        this.totalSize=iPage.getTotal();
        this.totalPages=iPage.getPages();
        this.content=iPage.getRecords();
    }

    public PageInfo() {
    }

    public PageInfo(Long pageNum, Long pageSize, Long totalSize, Long totalPages, List<T> content) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.totalPages = totalPages;
        this.content = content;
    }
}
