package com.czu.controller;

import com.czu.pojo.User;
import com.czu.service.impl.UserService;
import com.czu.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> list(){
        // 输入流in
        List<User> userList = userService.findAll();


        return userList;
    }
}
