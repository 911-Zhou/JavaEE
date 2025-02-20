package org.zdl.booksystem.mapper;

import org.apache.ibatis.annotations.*;
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
    @Select("select * from book_info where status !=0 order by id asc limit #{offset},#{PageSize}")
    public List<BookInfo> getListByPage(PageRequest request);

    //获取指定id图书
    @Select("select * from book_info where id = #{id}")
    public BookInfo getBookByID(Integer id);


    public Integer updateBook(BookInfo bookInfo);

    //删除图书
//    @Delete("delete from book_info where id = #{id}")
    @Update("update book_info set status = 0 where id = #{id}")
    public Integer deleteBook(Integer id);


    //批量删除
    public Integer batchDelBook(List<Integer> ids);
}
