package org.zdl.booksystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.zdl.booksystem.model.UserInfo;

@Mapper
public interface UserMapper {
    //登录
    @Select("select *  from user_info where user_name = #{username} and delete_flag = 0")
    public UserInfo SelectUser(String username);
}
