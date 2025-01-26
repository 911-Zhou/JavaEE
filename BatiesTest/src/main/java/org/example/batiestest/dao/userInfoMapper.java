package org.example.batiestest.dao;

import org.apache.ibatis.annotations.*;
import org.example.batiestest.pojo.User;

import java.util.List;

@Mapper
public interface userInfoMapper {
    //查询所有用户
    //    @Select("select id, username, password, age, gender, phone, delete_flag as deleteFlag, " +
//            "create_time as createTime, update_time as updateTime from user_info")
//    @Select("select id, username, password, age, gender, phone, delete_flag, " +
//            "create_time, update_time from user_info")
    @Select("select * from user_info")
    @Results(id = "resultMap",
            value = {
            @Result(column = "delete_flag",property = "deleteFlag"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime")
    })
    public List<User> GetUsers();

    //根据id查询用户
    @Select("select * from user_info where id = #{id}")
    @ResultMap(value = "resultMap")
    public List<User> GetUserById(Integer id);

    //新增用户
    @Insert("insert into user_info(id,username,password,age,phone) values (#{id},#{username},#{password},#{age},#{phone})")
    public Integer AddUser(User user);

    //修改用户
    @Update("update user_info  where username = #{username}")
    public void updateUsername(String username);

    //删除用户
    @Delete("delete from user_info where id = #{id}")
    public void delUser(Integer id);
}
