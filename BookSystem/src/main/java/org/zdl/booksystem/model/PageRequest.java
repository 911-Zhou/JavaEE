package org.zdl.booksystem.model;

import lombok.Data;

//分页请求类
@Data
public class PageRequest {
    private int CurrentPage = 1;

    private int PageSize = 10;

    //limit 语句数据起始位置(条)
    private int offset;

    public int getOffset()
    {
        return (CurrentPage-1)*PageSize;
    }

}
