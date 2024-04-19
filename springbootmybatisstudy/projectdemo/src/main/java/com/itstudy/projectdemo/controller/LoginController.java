package com.itstudy.projectdemo.controller;

import com.itstudy.projectdemo.pojo.Emp;
import com.itstudy.projectdemo.pojo.Result;
import com.itstudy.projectdemo.service.EmpService;
import com.itstudy.projectdemo.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 26977
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping()
    public Result login(@RequestBody Emp emp) {
        log.info("员工登录：{}", emp);
        Emp emp1 = empService.login(emp);

        //登录成功，生成令牌并且下发令牌
        if(emp1 != null){
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",emp1.getId());
            claims.put("username",emp1.getUsername());
            claims.put("name",emp1.getName());
            //jwt包含了登录的员工信息
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }
            return Result.error("用户名或者密码错误");

        //return emp1 != null ? Result.success() : Result.error("用户名或者密码错误");
        //return Result.success(emp1);
    }
}
