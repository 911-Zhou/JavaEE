package org.example.springwebdemo119.controller;

import org.example.springwebdemo119.pojo.message;
import org.example.springwebdemo119.serivce.messageWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/message")
@Controller
public class messageWallController {
    List<message> messageList = new ArrayList<>();
    @Autowired
    messageWallService messageWallService;

    @RequestMapping("/getList")
    @ResponseBody
    public List<message> getList(){
        System.out.println("返回数据");
        messageList = messageWallService.getMessageInfo();
        return messageList;
    }

    @RequestMapping("/public")
    @ResponseBody
    public String putMessage(@RequestBody message message1){
//        System.out.println("message："+message1);
        System.out.println("message获取:"+message1.getMessage());
        messageList.add(message1);
        return String.valueOf(messageWallService.AddMessage(message1));
    }

}
