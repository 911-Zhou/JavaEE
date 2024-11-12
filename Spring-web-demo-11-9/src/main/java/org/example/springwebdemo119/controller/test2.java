package org.example.springwebdemo119.controller;

import org.example.springwebdemo119.pojo.user;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}
