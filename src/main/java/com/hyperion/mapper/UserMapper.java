package com.hyperion.mapper;

import com.hyperion.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    //根据用户名查询用户
    User findByUserName(String username);
    //添加用户
    @Insert("insert into user(username,password,create_time,update_time) values(#{username},#{md5Pwd},now(),now())")
    void add(String username, String md5Pwd);
    @Update("update user set nickname = #{nickname},email = #{email},update_time=#{updateTime} where id = #{id}")
    void update(User user);
    @Update("update user set user_pic = #{avatarUrl},update_time = now() where id = #{id}")
    void updateAvatar(String avatarUrl,Integer id);
    @Update("update user set password = #{md5String},update_time = now() where id = #{id}")
    void updatePwd(String md5String, Integer id);
}
