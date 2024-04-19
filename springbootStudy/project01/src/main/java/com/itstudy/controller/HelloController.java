package com.itstudy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//请求处理类
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String  hello (){
        System.out.println("Hello Word");
        return "Hello Word qwq";
    }
}