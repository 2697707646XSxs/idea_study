package com.itstudy.projectdemo.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.itstudy.projectdemo.pojo.Result;
import com.itstudy.projectdemo.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
@Component//交给ioc容器
@Slf4j
//拦截器的使用，效果应该和过滤器差不多，这里与LoginFiltera类似，但是拦截器只拦截spring里面的资源，因为他是spring提供的
public class LoginInterceptor implements HandlerInterceptor {
    @Override//目标方法运行前运行，返回值true 放行   false 不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("方法运行 preHandle...");
        //获取url
        String url =  request.getRequestURL().toString();
        log.info("请求的url:{}",url);

        //判断url是login路径还是其他路径，登录即放行，否则进行
        if(url.contains("login")){
            log.info("登录操作，放行...");
            return true;
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
            return false;
        }

        //解析令牌，如果解析失败就返回错误信息

        try {//ctrl+alt + t 快速构建方法
            //解析令牌不报错就行了
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {//解析失败
            e.printStackTrace();//输出错误位置以及原因
            log.info("令牌被篡改或者已经过期,返回未登录的错误信息");
            //以下同理
            Result error =Result.error("NOT_LOGIN");
            String BugLogin = JSONObject.toJSONString(error);
            response.getWriter().write(BugLogin);
            return false;
        }

        //全部正确，放行
        log.info("令牌合法，放行");
       return true;
    }

/*    @Override//目标方法运行后运行 最后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        System.out.println("方法运行了 postHandle  yes");
    }

    @Override//目标方法运行后运行 最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    System.out.println("afterCompletion...");
    }*/
}
