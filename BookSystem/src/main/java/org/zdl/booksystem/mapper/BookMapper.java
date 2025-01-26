package org.zdl.booksystem.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.zdl.booksystem.model.BookInfo;
import org.zdl.booksystem.model.PageRequest;

import java.util.List;


//java bean   // 实体类
@Mapper
public  interface BookMapper {
    @Select("select * from book_info")
    public List<BookInfo> getBookList();

    //添加书籍
    @Insert("insert into book_info (book_name,author,`count`,price,publish,status) " +
            "values (#{bookName},#{author},#{count},#{price},#{publish},#{status})")
    public Integer InsertBook(BookInfo bookInfo);

    //数据条数
    @Select("select count(*) from book_info")
    public Integer Count();

    //排除已删除数据条数
    @Select("select count(*) from book_info where status!=0")
    public Integer AbleCount();

    //分页查询
    @Select("SELECT * from book_info limit #{}")
    public List<BookInfo> getListByPage(PageRequest request);
}
