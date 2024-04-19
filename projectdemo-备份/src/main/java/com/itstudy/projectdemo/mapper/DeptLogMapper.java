package com.itstudy.projectdemo.mapper;

import com.itstudy.projectdemo.pojo.Deptlog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 26977
 */
@Mapper
public interface DeptLogMapper {
    @Insert("insert into dept_log(create_time, description) values (#{createTime},#{description})")
    void insert(Deptlog log);
}
