package com.itstudy.Dao.impl;

import com.itstudy.Dao.EmpDao;
import com.itstudy.controller.pojo.Emp;
import com.itstudy.controller.utils.XmlParserUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Repository//把当前类交给IOC容器管理，成为IOC容器中的bean
public class EmpDaoA implements EmpDao {
    @Override
    public List<Emp> listEmp() {//加载并且解析emp.xml
        String file = this.getClass().getClassLoader().getResource("emp.xml").getFile();//动态获取文件路径
        System.out.println(file);
        List<Emp> empList = XmlParserUtils.parse(file, Emp.class);
        return empList;
    }
}
