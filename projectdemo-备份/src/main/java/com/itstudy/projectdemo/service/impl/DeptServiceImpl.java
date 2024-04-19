package com.itstudy.projectdemo.service.impl;

import com.itstudy.pojo.Dept;
import com.itstudy.pojo.Deptlog;
import com.itstudy.projectdemo.anno.Log;
import com.itstudy.projectdemo.mapper.DeptLogMapper;
import com.itstudy.projectdemo.mapper.DeptMapper;
import com.itstudy.projectdemo.mapper.EmpMapper;
import com.itstudy.projectdemo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
/**
 * @author 26977
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private DeptLogMapper deptLogMapper;
    @Override
    public List<Dept> list() {//实现查询全部部门数据接口方法
        List<Dept> deptList = deptMapper.list();
        return deptList;
    }

   /* 默认情况下只加@Transactional，只有运行异常才会回滚*/
    //@Transactional(rollbackFor = Exception.class)/*事务管理的注解，防止出现错误时导致数据不一致，一般在同一个方法是执行多个操作上面添加该注解,所有异常都会回滚*/
    @Transactional
    @Override
    public void delete(Integer id) {//删除部门
        /*根据id删除部门*/
        try {
            deptMapper.deleteById(id);
            /*根据部门id删除对应部门的员工操作*/
            empMapper.deleteBydeptId(id);
        } finally {/*底下的操作一般在Aop上面实现*/
            /*创建一个部门删除操作日志，无论删除是否完成，日志都会记录下来*/
            Deptlog deptlog = new Deptlog();
            deptlog.setCreateTime(LocalDateTime.now());
            deptlog.setDescription("执行了解散部门操作，此次解散的是"+id+"部门");
            deptLogMapper.insert(deptlog);
        }
    }
    @Log
    @Override
    public void insert(Dept dept) {//新增部门
        dept.setUpdateTime(LocalDateTime.now());
        dept.setCreateTime(LocalDateTime.now());
        deptMapper.insertByName(dept);
    }

    @Override
    public Dept getById(Integer id) {
       return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        deptMapper.updateByName(dept);
    }
}
