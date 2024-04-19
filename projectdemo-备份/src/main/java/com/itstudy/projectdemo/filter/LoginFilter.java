package com.itstudy.projectdemo.filter;

import com.alibaba.fastjson.JSONObject;
import com.itstudy.projectdemo.pojo.Result;
import com.itstudy.projectdemo.util.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;


@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    //servletRequest，获取请求参数，servletResponse响应请求结果
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;//强转servletRequest的ServletRequest类为HttpServletRequest，便于获得url
        HttpServletResponse response = (HttpServletResponse)servletResponse;//同理

        //获取url
        String url =  request.getRequestURL().toString();
        log.info("请求的url:{}",url);

        //判断url是login路径还是其他路径，登录即放行，否则进行
        if(url.contains("login")){
            log.info("登录操作，放行...");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //获取请求头里面的令牌token
        String jwt = request.getHeader("token");

        //判断令牌是否存在，错误即返回错误信息
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录的信息");
            Result error =  Result.error("NOT_LOGIN");
            //手动转换 对象--json(可以所有阿里巴巴的 fastJSON工具包)
            String NoLogin = JSONObject.toJSONString(error);
            response.getWriter().write(NoLogin);
            return;
        }

        //解析令牌，如果解析失败就返回错误信息

        try {//ctrl+alt + t 快速构建方法
            JwtUtils.parseJWT(jwt);//解析令牌不报错就行了
        } catch (Exception e) {//解析失败
            e.printStackTrace();//输出错误位置以及原因
            log.info("令牌被篡改或者已经过期,返回未登录的错误信息");
           Result error =Result.error("NOT_LOGIN");//同理
           String BugLogin = JSONObject.toJSONString(error);
            response.getWriter().write(BugLogin);
           return;
        }

        //全部正确，放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
