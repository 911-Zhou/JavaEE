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
}