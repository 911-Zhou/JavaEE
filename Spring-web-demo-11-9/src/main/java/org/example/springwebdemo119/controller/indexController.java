package org.example.springwebdemo119.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.HttpCookie;

@RequestMapping("/index")
@Controller
public class indexController {
    @RequestMapping("/login")
    @ResponseBody
    public Boolean login(String userName, String password, HttpServletResponse response){
        System.out.println("userName:"+userName+",password:"+password);
        Boolean result = userName.equals("admin")&&password.equals("admin")?true:false;
        //登录成功设置cookie session
        if(result){
            Cookie cookie = new Cookie("userName",userName);
            cookie.setMaxAge(24*60*60);
            cookie.setPath("/");
            response.addCookie(cookie);
            System.out.println("设置用户登录cookie");
        }
        return result;
    }
    
    @RequestMapping("/getUserName")
    @ResponseBody
    public String getUserName(@CookieValue("userName") String userName){
        //通过cookie获取用户名
        System.out.println("userName:"+userName);
        return userName;
    }
}
