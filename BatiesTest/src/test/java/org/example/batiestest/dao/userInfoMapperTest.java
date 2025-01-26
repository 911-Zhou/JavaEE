package org.example.batiestest.dao;

import org.example.batiestest.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class userInfoMapperTest {
    @Autowired
    userInfoMapper userInfoMapper;
    @Test 
    void getUsers() {
        List<User> users = userInfoMapper.GetUsers();
        users.forEach(user->{
            System.out.println(user.toString());
        });
    }

    @Test
    void getUserById() {
        List<User>users = userInfoMapper.GetUserById(4);
        users.forEach(user -> {
            System.out.println(user.toString());
        });
    }

    @Test
    void addUser() {
        User user = new User();
        user.setId(5);
        user.setUsername("zhaoliu");
        user.setPassword("zhaoliu");
        user.setAge(21);
        user.setPhone("11111111111");
        userInfoMapper.AddUser(user);
    }

    @Test
    void updateUsername() {

    }
}