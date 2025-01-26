package org.example.springwebdemo119.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.springwebdemo119.pojo.message;

import java.util.List;

@Mapper
public interface messageWallMapper {
    @Select("select * from message_info")
    List<message> queryAll();

    @Insert("insert into message_info (`from`,`to`, `message`) values (#{from},#{to},#{message})")
    Integer addMessage(message messageInfo);
}
