package org.example.springwebdemo119.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello")
public class print {
    @ResponseBody
    @RequestMapping(path = "/print1",method = RequestMethod.GET)
    public String print1(){
        return "hello Spring boot MVC";
    }

    @ResponseBody
    @RequestMapping(path = "/print2",method = RequestMethod.POST)
    public String print2(){
        return "hello Spring boot MVC";
    }

    @GetMapping("/print3")
    @ResponseBody
    public String print3(){
        return "hello Spring boot MVC";
    }

    @PostMapping("/print4")
    @ResponseBody
    public String print4(){
        return "hello Spring boot MVC";
    }
}
