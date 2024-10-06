package com.hyperion.mapper;

import com.hyperion.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    //根据用户名查询用户
    User findByUserName(String username);
    //添加用户
    @Insert("insert into user(username,password,create_time,update_time) values(#{username},#{md5Pwd},now(),now())")
    void add(String username, String md5Pwd);
}
