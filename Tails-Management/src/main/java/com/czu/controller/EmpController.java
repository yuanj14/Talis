package com.czu.controller;


import com.czu.mapper.EmpExprMapper;
import com.czu.pojo.*;
import com.czu.service.EmpExprService;
import com.czu.service.EmpService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * 员工管理
 */
@Slf4j
@RequestMapping("/emps") //公共前缀
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;
    @Autowired
    private EmpExprService empExprService;

    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.warn("page={}", empQueryParam);
        PageResult<Emp> data = empService.page(empQueryParam);
        return Result.success(data);
    }


    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.warn("save={}", emp);
        empService.save(emp);

        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.warn("delete={}", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.warn("getById={}", id);
        Emp data = empService.getInfo(id);
        return Result.success(data);
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息, {}", emp);
        empService.update(emp);
        return Result.success();
    }

}