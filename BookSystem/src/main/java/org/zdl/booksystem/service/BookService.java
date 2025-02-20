package org.zdl.booksystem.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.zdl.booksystem.Constants.constants;
import org.zdl.booksystem.model.*;
import org.zdl.booksystem.mapper.BookMapper;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;


    //构造注入
//    public BookService(BookDao bookDao) {
//        this.bookDao = bookDao;
//    }

    public List<BookInfo> getBookList(){
        //从dao获取数据
        List<BookInfo> bookInfos = bookMapper.getBookList();

        bookInfos.forEach(book->{
            switch (book.getStatus()){
                case 0:{
                    book.setStatusCN("无效");
                    break;
                }
                case 1:{
                    book.setStatusCN("可借阅");
                    break;
                }
                default:{
                    book.setStatusCN("不允许借阅");
                }
            }
        });

        return bookInfos;
    }

    public String AddBook(BookInfo bookInfo) {
        if(!StringUtils.hasLength(bookInfo.getBookName())||
                !StringUtils.hasLength(bookInfo.getAuthor())||
                bookInfo.getCount()==null ||
                bookInfo.getPrice()==null ||
                !StringUtils.hasLength(bookInfo.getPublish())||
                bookInfo.getStatus()==null
        ){
            return "请检查数据后重试.";
        }
        if(bookMapper.InsertBook(bookInfo)>0){
            return  "插入成功";
        }
        else {
            return "插入失败请联系管理员";
        }
    }

    public Result<PageResponse<BookInfo>> getListByPage(PageRequest request, HttpServletRequest httpServletRequest){
        //用户未登录判断
        HttpSession session = httpServletRequest.getSession(false);

        if(session==null || session.getAttribute(constants.SESSION_USER_KEY)==null){
            return Result.unLogin();
        }

        UserInfo userInfo = (UserInfo) session.getAttribute("session_user_key");

        if (userInfo==null || userInfo.getID()<0 ||
                "".equals(userInfo.getUserName())){
            return Result.unLogin();
        }



        Integer count = bookMapper.AbleCount();

        List<BookInfo> result = bookMapper.getListByPage(request);

        result.forEach(book->{
            switch (book.getStatus()){
                case 0:{
                    book.setStatusCN("无效");
                    break;
                }
                case 1:{
                    book.setStatusCN("可借阅");
                    break;
                }
                default:{
                    book.setStatusCN("不允许借阅");
                }
            }
        });

        return Result.success(new PageResponse<>(count,result));
    }


    public BookInfo getBookById(Integer id){
        return bookMapper.getBookByID(id);
    }

    /**
     * 修改图书
     * @param bookInfo
     * @return
     */
    public String updateBook(BookInfo bookInfo){
        Integer result = bookMapper.updateBook(bookInfo);
        if(result!=0)return "修改成功";
        else return "修改失败";
    }

    //删除图书
    public String deleteBook(Integer id){
        Integer result = bookMapper.deleteBook(id);
        if(result!=0){
            return "删除成功";
        }
        else return "删除失败";
    }

    //批量删除
    public void batchDelBook(List<Integer>ids){
        bookMapper.batchDelBook(ids);
    }
}
