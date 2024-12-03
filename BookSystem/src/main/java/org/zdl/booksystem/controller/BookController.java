package org.zdl.booksystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zdl.booksystem.Pojo.Book;
import org.zdl.booksystem.service.BookService;

import java.util.List;

@RequestMapping("/book")
@RestController
@Configuration
public class BookController {
    @Autowired
    private BookService bookService;

    //构造注入
//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }

    @RequestMapping("/getList")
    public List<Book> getBookList(){
        return bookService.getBookList();
    }


}
