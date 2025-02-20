package org.zdl.booksystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.zdl.booksystem.model.BookInfo;
import org.zdl.booksystem.model.PageRequest;
import org.zdl.booksystem.model.PageResponse;
import org.zdl.booksystem.model.Result;
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

    //分页获取图书列表
    @RequestMapping("/GetListByPage")
    public Result getListByPage(@ModelAttribute PageRequest request, HttpServletRequest httpServletRequest){
      log.info("获取图书列表, pageRequest:{}", request);
      Result result = bookService.getListByPage(request,httpServletRequest);
      //log.info("GetListByPage返回result:{}",result);
      return result;
    }


    //获取指定id图书
    @RequestMapping("/queryBookById")
    public BookInfo queryBookById(Integer bookId){
        return  bookService.getBookById(bookId);
    }

    //修改图书
    @RequestMapping("updateBook")
    public String updateBook(BookInfo bookInfo){
        log.info("updateBook参数："+bookInfo);
        return bookService.updateBook(bookInfo);
    }

    //删除图书
    @RequestMapping("deleteBook")
    public String deleteBook(Integer id){
        log.info("删除图书:"+id);
        return bookService.deleteBook(id);
    }

    //批量删除
    @RequestMapping("batchDelBook")
    public boolean batchDelBook(@RequestParam List<Integer> ids){
        log.info("批量删除,ids:{}",ids);
        try {
            bookService.batchDelBook(ids);
        } catch (Exception e) {
            log.error("删除异常,e:",e);
            return false;
        }
        return true;
    }
}
