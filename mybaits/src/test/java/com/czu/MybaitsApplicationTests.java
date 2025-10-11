package com.czu;

import com.czu.mapper.UserMapper;
import com.czu.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybaitsApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testFindAll(){
        List<User> userList = userMapper.findAll();
        userList.forEach(System.out::println);
    }

    @Test
    public void testDeleteById(){
        Integer i = userMapper.deleteById(2);
        System.out.println("删除影响的条数" + i);
    }


    @Test
    public void testInsert(){
        User user = new User(null, "gaoyuan", "11111", "高圆圆",18);
        userMapper.insert(user);
    }

    @Test
    public void testUpdate(){
        User user = new User(1, "guoduanduan", "77777", "果断点",18);
        userMapper.update(user);
    }

    @Test
    public void testSelect(){
        User person = userMapper.findone("123", "213");
        System.out.println(person);

    }
}
