package com.itstudy.projectdemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

import java.util.Arrays;

/**
 * @author 26977
 */
@Slf4j
//@Component（为了防止影响aop记录操作日志）
@Aspect//将当前类变aop类
@Order(1)/*使用@Order注解来给切面类排序，方法之前的越小越快，方法之后的越大越快*/
//spring boot aop的使用，aop(面对切面编程，面向方面编程)，就是面对特定方法编程
//下面展示计算业务执行耗时的方法，还可以添加记录操作日志，权限控制，事务管理...
public class TimeAspect {

    //execution根据签名来匹配
    /*完整的：execution (public void com.itstudy.projectdemo.service.impl.DeptServiceImpl.delete(java.lang.Integer)) ,这玩意只是单独一个方法*/
    /*关联多个方法：execution (* com.itstudy.projectdemo.service.*.delete(..)) || execution (* com.itstudy.projectdemo.service.*.insert(..)) ")   */
    @Pointcut("execution (* com.itstudy.projectdemo.service.*.*(..))")//这玩意适合范围通知
    //根据@annotation
    //@Pointcut("@annotation(com.itstudy.projectdemo.util.MyLog)")//这玩意适合精准通知

    public void pt(){}
    /*下面@Around是为了说明切入点，即在service包下的接口和类时，会使用下面的方法，下面的表达式即切入点表达式*/
    @Around("pt()")

    /* 下面为通知 */
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        //记录开始时间
        /*拿到当前时间的毫秒值*/
        long begin = System.currentTimeMillis();

        /*调用原始方法运行(这是必须的，在Around里面)*/
        Object result = joinPoint.proceed();

        /*获取目标方法的类名*/
        String classname = joinPoint.getTarget().getClass().getName();
        log.info("目标对象的类名：{}",classname);

        /*获取目标方法的方法名*/
        String methodName = joinPoint.getSignature().getName();
        log.info("目标对象的方法名：{}",methodName);

        /*获取方法运行传入的参数*/
        Object[] args = joinPoint.getArgs();
        log.info("获取方法运行传入的参数:{}", Arrays.toString(args));

        /*获取目标方法运行过后的返回值*/
        log.info("获取目标方法运行过后的返回值:{}",result);


        //记录结束时间，计算方法执行耗时
        long end = System.currentTimeMillis();
        /*通过joinPoint.getSignature()获得原始方法的签名*/
        log.info(joinPoint.getSignature() + "方法执行的耗时为：{}ms", end - begin);
        return result;

    }
}
/*使用Aop的好处（基于动态代理技术实现）
 * 代码无侵入
 * 减少重复代码
 * 提高开发效率
 * 维护方便
 * */
/*
 * @Around环绕通知，切入点之前或者之后都可以通知，所有才会在中间导入原始方法
 * @before切入点之前通知
 * @after切入点之后通知
 * @AfterReturning原始方法正常运行，返回之后通知
 * @AfterThrowing原始方法出现异常，抛出异常之后通知
 */
