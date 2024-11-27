package org.zdl.booksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.zdl.booksystem.Pojo.book;
import org.zdl.booksystem.dao.BookDao;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public List<book> getBookList(){
        List<book> books = bookDao.mockData();
        return books;
    }
}
