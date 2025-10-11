package com.czu.mapper;

import com.czu.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper //proxy --> auto component
public interface UserMapper {

    public List<User> findAll();

    @Delete("delete from user where id = #{id}")
    public Integer deleteById(Integer id);

    @Insert("insert into user(username, password, name, age) values (#{username}, #{password}, #{name}, #{age})")
    public void insert(User user);

    //更新用户
    @Update("update user set username = #{username}, password = #{password}, name = #{name}, age = #{age} where id = #{id}")
    public void update(User user);

    //查询
    @Select("select * from user where username = #{username} and password = #{password}")
    public User findone(@Param("username")String username,@Param("password") String password);
}
