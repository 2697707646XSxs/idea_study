package com.itstudy.projectdemo.controller;


import com.itstudy.pojo.Dept;
import com.itstudy.pojo.Result;
import com.itstudy.projectdemo.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * @author 26977
 */
//@Lazy//延迟bean对象的初始化，直到第一次使用
//@Scope("prototype")//设置bean非单例的
@RequestMapping("/depts")//使用这个就可以减少公共请求路径的编写
@RestController//既实现注入，也可以把返回数据转成json格式
@Slf4j//记录活动日志对象
public class DeptController {
    @Autowired
    private DeptService deptService;

    /*
    查询部门
    */
    @GetMapping()//现在请求参数格式必须是GET形式
//  @PostMapping("/depts")//现在请求参数格式必须是POST形式
    public Result lsit(){
        log.info("查询全部部门数据");
        //调用service查询部门数据
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }
    /*
    删除部门
    */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("输出选定的部门：{}",id);
        deptService.delete(id);
        return Result.success();
    }
    /*
    新增部门
     */
    @PostMapping()
    public Result insert(@RequestBody Dept dept){
        log.info("新增部门:{}",dept);
        deptService.insert(dept);
        return Result.success();
    }
    /*
    根据id响应--修改部门信息
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("获取部门id:{}",id);
        Dept deptList = deptService.getById(id);
        return Result.success(deptList);
    }
    @PutMapping()
    public Result update(@RequestBody Dept dept){
        log.info("根据部门id修改部门名字：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
