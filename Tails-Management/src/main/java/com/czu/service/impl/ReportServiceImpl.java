package com.czu.service.impl;

import com.czu.mapper.EmpMapper;
import com.czu.pojo.JobOption;
import com.czu.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
        
    @Override
    public JobOption getEmpJobData() {
        List<Map<String,Object>> list = empMapper.countEmpJobData();
//        log.error(list.toString());
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }
}