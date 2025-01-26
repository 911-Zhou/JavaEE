package org.zdl.booksystem.model;

import java.util.List;

//分页响应类
public class PageResponse<T> {
    //数据总数
    private int total;

    //当前页数据
    private List<T> records;

    public PageResponse(Integer total,List<T> records){
        this.total = total;
        this.records = records;
    }

}
