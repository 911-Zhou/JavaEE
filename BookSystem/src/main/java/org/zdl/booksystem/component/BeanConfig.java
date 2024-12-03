package org.zdl.booksystem.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zdl.booksystem.Pojo.Book;

@Configuration
public class BeanConfig {
    @Bean(name = "book1")
    public Book getBook(){
        Book book = new Book();
        book.setId("1");
        book.setBookName("情感学");
        book.setStatus(String.valueOf(1));
        book.setPrice((long)9.9);
        book.setCount(111);
        book.setAuthor("mr.zdl");
        book.setStatusCN("可借阅");
        return book;
    }

    @Bean(name = "book2")
    public Book getBook2(){
        Book book = new Book();
        book.setId("11");
        book.setBookName("经济学");
        book.setStatus(String.valueOf(1));
        book.setPrice((long)9.9);
        book.setCount(111);
        book.setAuthor("mr.zdl");
        book.setStatusCN("可借阅");
        return book;
    }

}
