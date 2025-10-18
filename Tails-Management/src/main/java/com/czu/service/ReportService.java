package com.czu.service;

import com.czu.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计各个职位的员工人数
     * @return
     */
    JobOption getEmpJobData();


    List<Map> getEmpGenderData();
}