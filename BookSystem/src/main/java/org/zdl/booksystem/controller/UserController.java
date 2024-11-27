package org.zdl.booksystem.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserController {
    @RequestMapping("/login")
    public Boolean login(String name, String password, HttpSession httpSession){
        //1.校验参数格式
        //2.查询数据库校验账号密码
        //3.正确，存储session
        System.out.println("登录请求触发");

        if(!StringUtils.hasLength(name) || !StringUtils.hasLength(password)){
            return false;
        }

        if("admin".equals(name) && "admin".equals(password)){
            httpSession.setAttribute("username",name);
            return true;
        }

        return false;
    }
    @RequestMapping("/loginTest")
    public void login(){
        System.out.println("登录请求触发");
    }
}
