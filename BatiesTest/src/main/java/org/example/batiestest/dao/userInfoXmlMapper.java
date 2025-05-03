package org.example.batiestest.dao;

import org.apache.ibatis.annotations.Mapper;
import org.example.batiestest.pojo.User;

import java.util.List;

@Mapper
public interface userInfoXmlMapper {
    List<User> queryAllUser();

    List<User> queryByUser(User user);

    Integer UpdateUser(User user);

    Integer deleteUsers(List<Integer> ids);
}
