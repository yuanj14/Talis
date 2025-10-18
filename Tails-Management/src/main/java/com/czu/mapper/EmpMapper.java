package com.czu.mapper;


import com.czu.pojo.Emp;
import com.czu.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
//    原生查询
//    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id")
//    Long count();
//
//    /**
//     * 分页查询
//     * @return List<Emp> Emp对象数据集合
//     */
//    // 参数名同名 无需@Param     List<Emp> list(@Param("start")Integer start, @Param("pageSize")Integer pageSize);
//    // 数据字段名跟Emp字段名相同可映射 小驼峰可映射 mysql:create_time -> Emp:createTime
//    @Select("select emp.*, dept.name as deptName from emp left join dept on emp.dept_id = dept.id " +
//            "order by emp.update_time desc limit #{start}, #{pageSize}")
//    List<Emp> list(Integer start, Integer pageSize);

    //  PageHelper
//    @Select("select emp.*, dept.name as deptName from emp left join dept on emp.dept_id = dept.id " +
//            "order by emp.update_time desc")
//    List<Emp> list(String name, Integer gender,
//                   LocalDate begin,
//                   LocalDate end);

    List<Emp> list(EmpQueryParam empQueryParam);
//    获取生成的主键 赋值id
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username}, #{name}, #{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);

    /**
     * 统计各个职位的员工人数
     */
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();

    @MapKey("name")
    List<Map> countEmpGenderData();
}