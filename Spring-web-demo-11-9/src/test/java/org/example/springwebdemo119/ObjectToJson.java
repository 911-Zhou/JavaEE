package org.example.springwebdemo119;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springwebdemo119.pojo.user;


public class ObjectToJson {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        user user1 = new user();

        user1.setName("zdl");
        user1.setGender(1);
        user1.setAge(20);

        //对象转json
        String s = objectMapper.writeValueAsString(user1);
        System.out.println("object 转 json"+s);

        //json转对象
        user user2 = objectMapper.readValue(s,user.class);
        System.out.println("json 转 object"+ user2);
    }
}
