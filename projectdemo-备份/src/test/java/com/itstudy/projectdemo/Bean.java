package com.itstudy.projectdemo;

import com.itstudy.projectdemo.controller.DeptController;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

//用来测试bean的获取以及使用
@SpringBootTest
public class Bean {
    @Autowired
    private ApplicationContext applicationContext;//IOC容器对象

    //最简单的获取bean对象
    @Test
    public void TextBean() {
        //根据bean的名称获取
        DeptController bean1 = (DeptController) applicationContext.getBean("deptController");
        System.out.println("根据bean的名称获取：" + bean1);

        //根据bean的类型获取
        DeptController bean2 = applicationContext.getBean(DeptController.class);
        System.out.println("根据bean的类型获取：" + bean2);

        //根据bean的名称和类型获取
        DeptController bean3 = applicationContext.getBean("deptController", DeptController.class);
        System.out.println("根据bean的名称加类型获取：" + bean3);
    }

    //查看bean的作用域通过Scope配置bean的作用域
    @Test
    public void testScope() {
        for (int i = 0; i < 10; i++) {
            DeptController bean1 = (DeptController) applicationContext.getBean("deptController");
            System.out.println("根据bean的名称获取：" + bean1);
        }
    }

    @Test
    public void textsaxReader() {
        System.out.println(applicationContext.getBean(SAXReader.class));
    }
}
