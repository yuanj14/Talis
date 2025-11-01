package com.czu.service;

import com.czu.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DeptService {

    public List<Dept> findAll();

    void delteById(Integer id);

    void add(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);

}
