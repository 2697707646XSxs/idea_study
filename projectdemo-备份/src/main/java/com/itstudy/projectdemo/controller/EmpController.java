package com.itstudy.projectdemo.controller;

import com.itstudy.projectdemo.pojo.Emp;
import com.itstudy.projectdemo.pojo.PageBean;
import com.itstudy.projectdemo.pojo.Result;
import com.itstudy.projectdemo.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author 26977
 */
@RestController
@RequestMapping("/emps")
@Slf4j
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping()
    public Result page(String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end,
                       @RequestParam(defaultValue = "1") Integer page, /*通过@RequestParam(defaultValue = "1")可以设置默认值*/
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询，参数：{},{},{},{},{},{}",name,gender,begin,end,page, pageSize);
        PageBean pageBean = empService.page(name,gender,begin,end,page, pageSize);
        return Result.success(pageBean);
    }


    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除员工，ids:{}",ids);
        empService.delete(ids);
        return Result.success();
    }


    @PostMapping()
    public Result insert(@RequestBody Emp emp){
        log.info("新增员工，emp:{}",emp);
        empService.insert(emp);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result select(@PathVariable Integer id){
        log.info("修改信息的员工id：{}",id);
        Emp emp = empService.select(id);
        return Result.success(emp);
    }
    @PutMapping()
    public Result update(@RequestBody Emp emp){
        log.info("修改员工的信息：{}",emp);
        empService.update(emp);
        return  Result.success();
    }
}
