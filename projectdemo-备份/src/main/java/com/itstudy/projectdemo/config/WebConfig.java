package com.itstudy.projectdemo.config;

import com.itstudy.projectdemo.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration//配置类 ， 拦截器的创建方法
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns:需要拦截哪一些内容;excludePathPatterns:不需要拦截哪一些内容
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
        //拦截所有路径/**   拦截/depts下所有路径即/depts/**    拦截/depts下一级路径即/depts/*
    }
}
