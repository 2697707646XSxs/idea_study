package com.itstudy.projectdemo.config;

import com.itstudy.projectdemo.controller.DeptController;
import org.dom4j.io.SAXReader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//配置类
public class CommonConfig {
    //声明第三方bean
    @Bean//并且将当前方法的返回值对象交给IOC容器管理，成为IOC容器的bean
    /*在参数里面传入数据类型实现依赖注入，这个就相当于
    * @Autowired
    * private DeptController deptController*/
     @ConditionalOnClass(name = "io.jsonwebtoken.Jwts")//环境中是否存在指定的类，存在才会将这个Bean加人ioc容器
    //@ConditionalOnBean/*判断ioc容器里面是否有该Bean对象（名称或者类型）,没有才注册bean到IOC容器*/
    //@ConditionalOnProperty(name = "name",havingValue = "itstudy")/*配置文件中存在指定的属性与值，才会将这个Bean加人ioc容器*/
    public SAXReader saxReader(DeptController deptController){
        System.out.println("注入的依赖："+deptController);
        return new SAXReader();
    }
}
