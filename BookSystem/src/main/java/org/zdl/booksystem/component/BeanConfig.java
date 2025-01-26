package org.zdl.booksystem.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zdl.booksystem.model.BookInfo;

import java.math.BigDecimal;

@Configuration
public class BeanConfig {
    @Bean(name = "book1")
    public BookInfo getBook(){
        BookInfo bookInfo = new BookInfo();
        bookInfo.setId("1");
        bookInfo.setBookName("情感学");
        bookInfo.setStatus(1);
        bookInfo.setPrice(BigDecimal.valueOf(9.9));
        bookInfo.setCount(111);
        bookInfo.setAuthor("mr.zdl");
        bookInfo.setStatusCN("可借阅");
        return bookInfo;
    }

    @Bean(name = "book2")
    public BookInfo getBook2(){
        BookInfo bookInfo = new BookInfo();
        bookInfo.setId("11");
        bookInfo.setBookName("经济学");
        bookInfo.setStatus(1);
        bookInfo.setPrice(BigDecimal.valueOf((long)9.9));
        bookInfo.setCount(111);
        bookInfo.setAuthor("mr.zdl");
        bookInfo.setStatusCN("可借阅");
        return bookInfo;
    }

}
