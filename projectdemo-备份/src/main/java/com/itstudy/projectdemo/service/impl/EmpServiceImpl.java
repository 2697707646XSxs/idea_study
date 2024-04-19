package com.itstudy.projectdemo.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itstudy.projectdemo.mapper.EmpMapper;
import com.itstudy.projectdemo.pojo.Emp;
import com.itstudy.projectdemo.pojo.PageBean;
import com.itstudy.projectdemo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    /*   @Override
       public PageBean page(Integer page, Integer pageSize) {
   //            获得总记录数
           Long count = empMapper.count();
   //        获得分页查询的记录表
           Integer start = (page - 1) * pageSize;
           List<Emp> empList = empMapper.page(start, pageSize);
   //        封装数据
           PageBean pageBean = new PageBean(count,empList);
           return pageBean;
       }*/
    @Override
    public PageBean page(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize) {
        //设置分页参数
        PageHelper.startPage(page, pageSize);
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        //执行查询
        Page<Emp> p = (Page<Emp>) empList;
        /*封装shj*/
        return new PageBean(p.getTotal(), p.getResult());
    }


    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void insert(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp select(Integer id) {
        return empMapper.select(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByUsernamandPassword(emp);
    }
}
