package org.example.springwebdemo119.controller;

import org.example.springwebdemo119.pojo.message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/message")
@Controller
public class messageWallController {
    List<message> messageList = new ArrayList<>();

    @RequestMapping("/getList")
    @ResponseBody
    public List<message> getList(){
        System.out.println("返回数据");
        return messageList;
    }

    @RequestMapping("/public")
    @ResponseBody
    public String putMessage(@RequestBody message message1){
        System.out.println(message1);
        messageList.add(message1);
        return "1";
    }

}
