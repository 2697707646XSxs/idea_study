package com.itstudy.projectdemo.filter;

import jakarta.servlet.*;

import java.io.IOException;
/*@WebFilter(urlPatterns = "/*")//拦截所有请求*/
/*@WebFilter(urlPatterns = "/login")//拦截特定请求*/
//@WebFilter(urlPatterns = "/emps/*")//拦截特定请求群
public class DemoFilter  implements Filter {
    @Override//初始化方法，只调用一次
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("init 初始化方法执行了");
    }

    @Override//拦截请求之后都会调用一次，调用多次
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Dome doFilter  拦截到了请求：需要登录获取令牌");
        //放行，继续运行，我觉得应该是先从login那里看令牌获取为true,true继续
        filterChain.doFilter(servletRequest, servletResponse);//通过filterChain.doFilter将拦截到的请求释放到下一个拦截器，如果没有就到资源那里
        System.out.println("Dome doFilter  放行了请求：从这里进行操作");
    }

    @Override//销毁程序的方法，只使用一次
    public void destroy() {
        Filter.super.destroy();
        System.out.println("destroy 销毁方法执行了");
    }
    //过滤器链通过过滤器类名自然排序（从小到大执行）
}
