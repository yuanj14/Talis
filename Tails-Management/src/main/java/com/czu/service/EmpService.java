package com.czu.service;

import com.czu.pojo.Emp;
import com.czu.pojo.EmpQueryParam;
import com.czu.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
//     PageResult<Emp> page(Integer page, Integer pageSize,
//                          String name, Integer gender,
//                          LocalDate begin,
//                          LocalDate end);

    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);
}
