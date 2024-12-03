package org.zdl.booksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zdl.booksystem.Pojo.Book;
import org.zdl.booksystem.dao.BookDao;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDao bookDao;


    //构造注入
//    public BookService(BookDao bookDao) {
//        this.bookDao = bookDao;
//    }

    public List<Book> getBookList(){
        List<Book> Books = bookDao.mockData();
        return Books;
    }
}
