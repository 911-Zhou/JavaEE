package org.example.springwebdemo119.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.springwebdemo119.pojo.user;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Controller
@RequestMapping("/test2")
public class test2 {
    @ResponseBody
    @RequestMapping("/getParam1")
    public String getParam1(Integer num){
        return "获取参数："+num;
    }

    @ResponseBody
    @RequestMapping("/getParam2")
    public String getParam2(String num){
        return "获取参数:" + num;
    }

    @ResponseBody
    @RequestMapping("/getParam3")
    public String getParam3(int num){
        return "获取参数："+num;
    }


    //参数绑定
    @ResponseBody
    @RequestMapping("/getparam4")
    public String getParam4(@RequestParam(value = "username",required = false) String name){
        return "获取参数："+ name;
    }

    @ResponseBody
    @RequestMapping("/getparam5")
    public String getParam5(String name, int age){
        return "get参数：" + name + " "+ age;
    }

    @ResponseBody
    @RequestMapping("/getparam6")
    public String getParam6(@RequestParam List<String> list){
        return "list: " + String.join(", ", list);
    }

    @ResponseBody
    @RequestMapping("getparam7")
    public String getParam7(@RequestBody user user1){
        return "user:" + user1;
    }

    @ResponseBody
    @RequestMapping("getparam8")
    public String getParam8(@RequestBody List<user>users){
        return "user:" + users.toString();
    }

    @ResponseBody
    @RequestMapping("getparam9/{name}")
    public String getParam9(@PathVariable String name){
        user user1 = new user();
        user1.setName(name);
        user1.setAge(20);
        user1.setGender(1);
        return user1.toString();
    }


    //上传文件
    @ResponseBody
    @RequestMapping("/putImage")
    public String getImage(@RequestPart("Image") MultipartFile multipartFile){
        //返回文件名
        String name = multipartFile.getOriginalFilename();
        return name;
    }

    //Cookie
    @ResponseBody
    @RequestMapping("/getCookie")
    public String getCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies==null)return "无cookie";
        Stream<Cookie> stream = Arrays.stream(cookies);
        stream.forEach(cookie -> System.out.println("Cookie：name = "+cookie.getName()+"value = " + cookie.getValue()));
        return "获取cookie成功";
    }

    @ResponseBody
    @RequestMapping("/getCookie2")
    public String getCookie2(@CookieValue("bitejava112") Cookie cookie){
        if(cookie!=null) return cookie.getName() + ":" +cookie.getValue();
        return "无cookie";
    }

    //设置Cookie
    @ResponseBody
    @RequestMapping("/setCookie")
    public String setCookie(HttpServletResponse response){
        Cookie  cookie = new Cookie("mju2022","zdl");
        cookie.setMaxAge(24*60*60);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "设置 cookie success";
    }

    //Session
    @ResponseBody
    @RequestMapping("/setSession")
    public String setSession(HttpSession session){
        session.setAttribute("username", "admin");
        session.setAttribute("userRole", "manager");
        session.setAttribute("age", "20");
        return "Session 已设置：用户名为 admin，角色为 manager";
    }


    @ResponseBody
    @RequestMapping("/getSession")

    public String  getSession(HttpServletRequest httpServletRequest){
        HttpSession session = httpServletRequest.getSession(false);
        //判空
        if(session==null)return "session未设置";
        String username = (String) session.getAttribute("username");
        System.out.println(username);
        return "从session中获取username-->"+username;
    }

    @ResponseBody
    @RequestMapping("/getSession2")
    //直接通过 Spring MVC 控制器方法的参数获取 HttpSession 时，session 通常不会为 null，
    // 因为 Spring 默认会为每个请求自动创建 Session（即使是新的 Session）。
    // 这就是为什么你在以下代码中，即使 Session 中没有任何内容，session 本身也不会为 null。
    public String getSession2(HttpSession session){
//        if(session==null)return "Session为空";
        //解决方案
        if(!session.getAttributeNames().hasMoreElements())return "session为空";
        String username = (String) session.getAttribute("username");
        System.out.println("session.get-->"+session.getAttribute("age"));
        return "从session中获取username-->"+username;
    }

    @ResponseBody
    @RequestMapping("/getSession3")
    public String getSession3(@SessionAttribute("username") String username){
        return "从session中获取username-->"+username;
    }


}
