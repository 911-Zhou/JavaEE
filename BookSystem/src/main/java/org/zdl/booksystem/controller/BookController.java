package org.zdl.booksystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zdl.booksystem.Pojo.book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/book")
@RestController
public class BookController {
    List<book> bookList = new ArrayList<>();

    @RequestMapping("/getList")
    public List<book> getBookList(){
        bookList = mockData();
        return bookList;
    }

    private List<book> mockData() {
        for (int i = 0; i < 15; i++) {
            book example = new book();
            example.setId(UUID.randomUUID().toString());
            example.setBookName("图书"+i);
            example.setStatus(String.valueOf(1));
            example.setPrice((long) (Math.random() * 100 +10));
            example.setCount(20);
            example.setAuthor("mr.zdl");
            example.setStatusCN("可借阅");
            bookList.add(example);
        }
        return bookList;
    }

}
