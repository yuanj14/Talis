package com.czu.service;

import com.czu.pojo.EmpExpr;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmpExprService {
    void insertBatch(List<EmpExpr> exprList);
}
