package com.itstudy.projectdemo.service;


import com.itstudy.pojo.Dept;

import java.util.List;

public interface DeptService {

    List<Dept> list();/*用来查询全部部门数据的*/

    void delete(Integer id);/*删除部门*/

    void insert(Dept dept);/*新增部门*/

    Dept getById(Integer id);/*根据id查询部门信息*/

    void update(Dept dept);/*根据id，修改信息，*/
}
