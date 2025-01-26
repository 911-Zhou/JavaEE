package org.example.springwebdemo119.serivce;

import org.example.springwebdemo119.mapper.messageWallMapper;
import org.example.springwebdemo119.pojo.message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class messageWallService {
    @Autowired
    messageWallMapper messageWallMapper;

    public List<message> getMessageInfo(){
        return  messageWallMapper.queryAll();
    }

    public Integer AddMessage(message messageInfo){
        return messageWallMapper.addMessage(messageInfo);
    }
}
