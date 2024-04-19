package com.itstudy.projectdemo.service;

import com.itstudy.projectdemo.pojo.Emp;
import com.itstudy.projectdemo.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /*
    员工查询
     */
    PageBean page(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize);
    /*
    批量删除员工
     */
    void delete(List<Integer> ids);
    /*
    批量插入员工
     */
    void insert(Emp emp);

    Emp select(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
