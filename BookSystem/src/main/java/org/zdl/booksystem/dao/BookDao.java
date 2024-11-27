package org.zdl.booksystem.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.zdl.booksystem.Pojo.book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


//java bean   // 实体类
@Repository
public class BookDao {
    public List<book> mockData() {
        List<book> bookList = new ArrayList<>();
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
