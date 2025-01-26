package org.zdl.booksystem.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zdl.booksystem.mapper.UserMapper;
import org.zdl.booksystem.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    public Boolean login(String name, String password, HttpSession httpSession, HttpServletResponse httpServletResponse){
        //1.校验参数格式
        //2.查询数据库校验账号密码
        //3.正确，存储session
        System.out.println("登录请求触发:");
        System.out.println("username："+name+";password：" + password);

        Boolean result  = userService.checkUserLogin(name,password,httpSession,httpServletResponse);

        return result;
    }
    @RequestMapping("/loginTest")
    public void login(){
        System.out.println("登录请求触发");
    }
}
