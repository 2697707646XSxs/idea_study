package com.itstudy.controller;

import com.itstudy.controller.pojo.Result;
import com.itstudy.controller.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
public class requestcontroller {//原始参数（不建议使用）

    //public class requestcontroller {
//    @RequestMapping("/simpleParam")
//    public String simpleParam(String name,Integer age){//直接定义形参，自动转换类型
//        //获取请求参数
//        System.out.println(name+":"+age);
//        return "OK";
//    }
//}
    /*简单参数，不方便大数据响应*/
/*@RestController
public class requestcontroller {
    @RequestMapping("/simpleParam")
    public String simpleParam(@RequestParam(name="name",required = false) String names, Integer age){//这样导致服务端请求名字和后端可以不一样，并且required = false不会报错
        //这里的name声明的是服务端的name
        //获取请求参数
        System.out.println(names+":"+age);
        return "OK";
    }
}*/
    /*简单实体参数，通过类方法进行检查*//*
    @RequestMapping("/simplePojo")
    public String simplePojo(User user) {
        System.out.println(user);
        return "OK";
    }*/
    /*    复杂实体参数*/
/*    @RequestMapping("/complexPojo")
    public String complexPojo(User user) {
        System.out.println(user);
        return "OK";
    }*/
    /*    使用数组进行数据封装，请求的参数名和数组变量名相同*/
/*@RequestMapping("/arraysPojo")
public String arraysPojo(String[] arrays) {
    System.out.println(Arrays.toString(arrays));
    return "OK";
}*/
    /*使用集合进行封装，请求的参数名和集合名字相同*/
/*    @RequestMapping("/ListPojo")//必须使用RequestParam才能将数据封装进集合
    public String ListPojo(@RequestParam List<String> arrays) {
        System.out.println(arrays);
        return "OK";
    }*/
/*    日期参数*//*
*//*    通过DateTimeFormat定义日期的形式，传入数据的时候必须按这个格式来
            pattern = "yyyy-mm-hh"*//*
    *//*LocalDateTime日期类型的变量名*//*
    @RequestMapping("/DatePojo")//必须使用RequestParam才能将数据封装进集合,貌似不能只有yyyy-MM-dd，不然会识别从String
    public String DatePojo(@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")LocalDateTime uptime) {
        System.out.println(uptime);
        return "OK";
    }*/
/*    JSOn形式参数*//*输入比较特别，不是get或者post
    {
        "name":"is",
            "age":"18",
            "address":{
        "home":"jiexi",
                "city":"jieyang"
    }

    }
        @RequestMapping("/JSONPojo")
    public String JSONPojo(@RequestBody User user) {
            System.out.println(user);
            return "OK";
        }*/
    //路径参数,网页路径里面传递参数,不加@PathVariable没办法获取参数
/*    @RequestMapping("/path/{id}/{name}")
    public String pathParam(@PathVariable Integer id,@PathVariable String name){
        System.out.println(id + name);
        return (name+ id);//return 作为响应返回给浏览器
    }*/




    //统一的请求响应格式，配合Resilt
    @RequestMapping("/simpleParam")
    public Result simpleParam(String name, Integer age){//直接定义形参，自动转换类型
        //获取请求参数
        System.out.println(name+":"+age);
        return Result.success();
    }
    /*简单参数，不方便大数据响应*/
    @RequestMapping("/simpleParam2")
    public Result simpleParam1(@RequestParam(name="name",required = false) String names, Integer age){//这样导致服务端请求名字和后端可以不一样，并且required = false不会报错
        //这里的name声明的是服务端的name
        //获取请求参数
        System.out.println(names+":"+age);
        return Result.success(names+"+"+age);
    }
}

