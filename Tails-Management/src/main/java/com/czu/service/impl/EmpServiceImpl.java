package com.czu.service.impl;


import com.czu.mapper.EmpExprMapper;
import com.czu.mapper.EmpMapper;
import com.czu.pojo.Emp;
import com.czu.pojo.EmpExpr;
import com.czu.pojo.EmpQueryParam;
import com.czu.pojo.PageResult;
import com.czu.service.EmpExprService;
import com.czu.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprService empExprService;
    @Autowired
    private EmpExprMapper empExprMapper;
//    原生Impl
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        Long total = empMapper.count();
//
//        int start = (page - 1) * pageSize;
//
//        List<Emp> rows = empMapper.list(start, pageSize);
//
//
//        return new PageResult<Emp>(total, rows);
//    }


    /**
     * PageHelper 实现分页查询
     *
     * @return
     */
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize,
//                                String name, Integer gender,
//                                LocalDate begin,
//                                LocalDate end) {
//        // res = (Page<Emp>) res
//        // format localDateTime
//        PageHelper.startPage(page, pageSize);
//        List<Emp> empList = empMapper.list(name, gender, begin, end);
//        Page<Emp> p = (Page<Emp>) empList;
//
//        return new PageResult<Emp>(p.getTotal(), p.getResult());
//    }


    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> empList = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>) empList;

        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Transactional
    @Override
    public void save(Emp emp) {
        // 员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);

        // 员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprService.insertBatch(exprList);
        }
    }

    @Transactional
    @Override
    public void deleteByIds(List<Integer> ids) {
        // 员工信息
        empMapper.deleteByIds(ids);
        // 工作经验信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2. 根据员工ID删除员工的工作经历信息 【删除老的】
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //3. 新增员工的工作经历数据 【新增新的】
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }

}