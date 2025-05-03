package org.example.batiestest.dao;

import org.example.batiestest.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class userInfoXmlMapperTest {
    @Autowired
    userInfoXmlMapper UserInfoXmlMapper;

    @Test
    void queryAllUser() {
        List<User> result = UserInfoXmlMapper.queryAllUser();
        result.forEach(item->{
            System.out.println(item);
        });
    }

    @Test
    void queryByUser() {
        User user = new User();
//        user.setAge(18);
//        user.setGender(1);
        List<User> result  = UserInfoXmlMapper.queryByUser(user);
        result.forEach(item->{
            System.out.println(item);
        });
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setId(2);
        user.setAge(20);
//        user.setGender(0);
        UserInfoXmlMapper.UpdateUser(user);
    }

    @Test
    void deleteUsers() {
        List<Integer> ids = List.of(6,7,8);
        UserInfoXmlMapper.deleteUsers(ids);
    }
}