package org.zdl.booksystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.zdl.booksystem.model.BookInfo;
import org.zdl.booksystem.model.PageRequest;
import org.zdl.booksystem.model.PageResponse;
import org.zdl.booksystem.service.BookService;

import java.util.List;

@RequestMapping("/book")
@RestController
@Configuration
@Slf4j
public class BookController {
    @Autowired
    private BookService bookService;

    //构造注入
//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }

    @RequestMapping("/getList")
    public List<BookInfo> getBookList(){
        return bookService.getBookList();
    }

    //添加图书
    @RequestMapping("/AddBook")
    public String AddBook(@ModelAttribute BookInfo bookInfo){
        System.out.println("添加图书");
        System.out.println(bookInfo);
        return bookService.AddBook(bookInfo);
    }

    @RequestMapping("/GetListByPage")
    public PageResponse<BookInfo> getListByPage(PageRequest request){
      log.info("获取图书列表, pageRequest:{}", request);
      PageResponse<BookInfo> result = bookService.getListByPage(request);
      return result;
    }
}
