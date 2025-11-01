package com.czu.controller;

import com.czu.pojo.Dept;
import com.czu.pojo.Result;
import com.czu.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 网络请求 和提取url信息
public class DeptController {

    @Autowired
    private DeptService deptService;

    // 响应结果 统一封装类Result
    @GetMapping("/depts")
    public Result FindAll() {
        List<Dept> data = deptService.findAll();
//        System.out.println(data);
        return Result.success(data);
    }


//    @RequestParam 必须传递 error400 default
//    @DeleteMapping("/depts")
//    public Result Delete(@RequestParam(value = "id", required = false) Integer id) {
//        System.out.println(id);
//        return Result.success();
//    }

    // url参数名一致 省略requestParam url? id = ?
    @DeleteMapping("/depts")
    public Result Delete(Integer id) {
//        System.out.println(id);
        deptService.delteById(id);
        return Result.success();
    }

    // 请求体Json @RequestBody 对象解构赋值 url request@body
    @PostMapping("/depts")
    public Result Add(@RequestBody Dept dept) {
//        System.out.println(dept);
        deptService.add(dept);
        return Result.success();
    }

    // 路径参数的获取 @Pathvariable("id"), 形参一致 省略@values
    @GetMapping("/depts/{id}")
    public Result Query(@PathVariable Integer id) {

        System.out.println(id);
        Dept data =  deptService.getById(id);

        return Result.success(data);
    }


    @PutMapping("/depts")
    public Result Update(@RequestBody Dept dept) {

        deptService.update(dept);

        return Result.success();
    }


}
