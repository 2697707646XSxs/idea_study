package com.itstudy.projectdemo.aop;

import com.alibaba.fastjson.JSONObject;
import com.itstudy.projectdemo.mapper.OperateLogMapper;
import com.itstudy.projectdemo.pojo.OperateLog;
import com.itstudy.projectdemo.util.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author 26977
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itstudy.projectdemo.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {

        /*操作人的id*/
        String jwt = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(jwt);
        Integer Userid = (Integer) claims.get("id");

        /*操作时间*/
        LocalDateTime operateTime = LocalDateTime.now();

        /*获取目标方法的类名*/
        String classname = joinPoint.getTarget().getClass().getName();

        /*获取目标方法的方法名*/
        String methodName = joinPoint.getSignature().getName();

        /*获取方法运行传入的参数*/
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        //记录开始时间，计算方法执行耗时
        long begin = System.currentTimeMillis();

        /*调用原始方法运行(这是必须的，在Around里面)*/
        Object proceed = joinPoint.proceed();

        //记录结束时间，计算方法执行耗时
        long end = System.currentTimeMillis();
        Long costTime = end - begin;

        /*获取方法的返回值,转换为json格式*/
        String returnValue = JSONObject.toJSONString(proceed);

        OperateLog log1 = new OperateLog(null,Userid,operateTime,classname,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(log1);
        log.info("面对切面编程成功:{}",log1);
        return proceed;
    }
}
