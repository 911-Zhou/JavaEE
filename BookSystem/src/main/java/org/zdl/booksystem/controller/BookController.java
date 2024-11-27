package org.zdl.booksystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zdl.booksystem.Pojo.book;
import org.zdl.booksystem.service.BookService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/book")
@RestController
@Configuration
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/getList")
    public List<book> getBookList(){
        return bookService.getBookList();
    }


}
