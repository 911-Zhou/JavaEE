package org.example.springwebdemo119.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
