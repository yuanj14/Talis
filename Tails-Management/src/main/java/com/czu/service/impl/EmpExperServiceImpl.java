package com.czu.service.impl;

import com.czu.mapper.EmpExprMapper;
import com.czu.mapper.EmpMapper;
import com.czu.pojo.EmpExpr;
import com.czu.service.EmpExprService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpExperServiceImpl implements EmpExprService {
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public void insertBatch(List<EmpExpr> exprList) {
        empExprMapper.insertBatch(exprList);
    }
}
