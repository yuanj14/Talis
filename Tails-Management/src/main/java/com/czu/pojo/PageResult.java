package com.czu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private Long total;
    private List<T> rows;
}
// 返回json格式 必须跟前端统一

//{
//  "code": 1,
//  "msg": "success",
//  "data": {
//    "total": 30,
//    "rows": [T1, T2, T3] list<T>
//    }
//}