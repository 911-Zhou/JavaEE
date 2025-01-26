package org.zdl.booksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.zdl.booksystem.model.BookInfo;
import org.zdl.booksystem.mapper.BookMapper;
import org.zdl.booksystem.model.PageRequest;
import org.zdl.booksystem.model.PageResponse;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;


    //构造注入
//    public BookService(BookDao bookDao) {
//        this.bookDao = bookDao;
//    }

    public List<BookInfo> getBookList(){
        //从dao获取数据
        List<BookInfo> bookInfos = bookMapper.getBookList();

        bookInfos.forEach(book->{
            switch (book.getStatus()){
                case 0:{
                    book.setStatusCN("无效");
                    break;
                }
                case 1:{
                    book.setStatusCN("可借阅");
                    break;
                }
                default:{
                    book.setStatusCN("不允许借阅");
                }
            }
        });

        return bookInfos;
    }

    public String AddBook(BookInfo bookInfo) {
        if(!StringUtils.hasLength(bookInfo.getBookName())||
                !StringUtils.hasLength(bookInfo.getAuthor())||
                bookInfo.getCount()==null ||
                bookInfo.getPrice()==null ||
                !StringUtils.hasLength(bookInfo.getPublish())||
                bookInfo.getStatus()==null
        ){
            return "请检查数据后重试.";
        }
        if(bookMapper.InsertBook(bookInfo)>0){
            return  "插入成功";
        }
        else {
            return "插入失败请联系管理员";
        }
    }

    public PageResponse<BookInfo> getListByPage(PageRequest request){
        Integer count = bookMapper.AbleCount();


    }
}
