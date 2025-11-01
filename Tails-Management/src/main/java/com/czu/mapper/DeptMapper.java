package com.czu.mapper;

import com.czu.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    // Mapper 数据层 操作数据库
    // sql语句 + 函数声明 -> {}
    @Select("select id, name, create_time, update_time from dept order by update_time desc ")
    List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into dept (name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void add(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
