package com.itstudy.controller;

import com.aliyun.oss.AliOssUtils;
import com.itstudy.Service.EmpService;
import com.itstudy.Service.inpl.EmpServiceA;
import com.itstudy.controller.pojo.Emp;
import com.itstudy.controller.pojo.Result;
import com.itstudy.controller.utils.XmlParserUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController(value = "huangchao")
public class EmpController {
   // @Autowired//程序运行的时候，ioc会提供该类型的bean对象，并赋给该变量
    @Autowired
    private EmpService empService;//都是面向接口，创建对象
    @RequestMapping("listEmp")
    //响应数据
    public Result list() {
        List<Emp> empList = empService.listEmp();
        return Result.success(empList);
    }
}
