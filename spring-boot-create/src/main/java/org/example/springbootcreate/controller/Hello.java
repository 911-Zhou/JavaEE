package org.example.springbootcreate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Hello {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello spring";
    }

    @RequestMapping("/hello2")
    public String hello(Model model) {
//        model.addAttribute("message", "");
        return "hello"; // 返回视图名称
    }

}
