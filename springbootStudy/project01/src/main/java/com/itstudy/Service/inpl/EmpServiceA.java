package com.itstudy.Service.inpl;

import com.itstudy.Dao.EmpDao;
import com.itstudy.Dao.impl.EmpDaoA;
import com.itstudy.Service.EmpService;
import com.itstudy.controller.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service//进入容器的关键词
public class EmpServiceA implements EmpService {
    @Autowired
    private EmpDao empDao;/*创建一个接口对象，拿到从EmpDao的数据*/

    @Override
    public List<Emp> listEmp() {
        List<Emp> empList = empDao.listEmp();//调用EmpDao,获取数据
        //将数据进行转换处理
        empList.stream().forEach(emp -> {
            String gender = emp.getGender();
            if ("1".equals(gender)) {
                emp.setGender("男");
            } else if ("2".equals(gender)) {
                emp.setGender("女");
            }
        });
        empList.stream().forEach(emp -> {
            String job = emp.getJob();
            if ("1".equals(job)) {
                emp.setJob("讲师");
            } else if ("2".equals(job)) {
                emp.setJob("班主任");
            } else if ("3".equals(job)) {
                emp.setJob("就业指导");
            }
        });
        return empList;
    }
}
