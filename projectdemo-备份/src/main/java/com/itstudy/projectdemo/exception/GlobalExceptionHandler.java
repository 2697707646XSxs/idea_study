package com.itstudy.projectdemo.exception;

import com.itstudy.projectdemo.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 26977
 * 全局异常处理器，用来处理项目报出来的异常信息
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /*捕获所有异常*/
    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex){
        ex.printStackTrace();
        return Result.error("对不起，操作失败，请联系管理员");
    }
}
