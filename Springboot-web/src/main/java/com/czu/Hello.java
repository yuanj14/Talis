package com.czu;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {
    @RequestMapping("/hello")
    public String Hello(String name) {
        System.out.println(name);
        return "Hello " + name;
    }
}
