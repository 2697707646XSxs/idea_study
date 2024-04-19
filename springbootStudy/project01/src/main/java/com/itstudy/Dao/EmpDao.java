package com.itstudy.Dao;

import com.itstudy.controller.pojo.Emp;

import java.util.List;

public interface EmpDao {
    //获取员工列表
    public List<Emp> listEmp();
}
