package com.itstudy.projectdemo.mapper;

import com.itstudy.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select  * from dept")
    List<Dept> list();//查询全部部门数据
    /*
    * 根据id删除部门
    *
    * */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);
    @Insert("insert into dept(name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void insertByName(Dept dept);
    @Select("select * from dept where id = #{id}")
    Dept getById(Integer id);
    @Update("update dept set name = #{name},create_time = #{createTime} where id = #{id}")/*#{}里面记得驼峰命名，一定要记住啊*/
    void updateByName(Dept dept);
}
