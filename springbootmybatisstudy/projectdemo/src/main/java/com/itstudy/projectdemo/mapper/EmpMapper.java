package com.itstudy.projectdemo.mapper;

import com.itstudy.projectdemo.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

//    查询员工总记录数

/*    @Select("select count(*) from emp")
    public Long count();


//    进行分页查询操作
    @Select("select * from emp limit #{start},#{pageSize}")
    public List<Emp> page(Integer start, Integer pageSize);*/

    /*员工信息的查询，利用pagehelper插件综合上面的的代码功能*/
    //  @Select("select * from emp")
    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp select(Integer id);

    //@Update("update emp set username = #{username},name = #{name},gender = #{gender},image = #{image},job = #{job}, entrydate = #{entrydate},deptId = #{deptId},updateTime = #{updateTime} where id = #{id}")
    void update(Emp emp);

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getByUsernamandPassword(Emp emp);

    /**
     *根据部门id来删除该部门员工信息
     * @param deptId
     */
    @Delete("delete from emp where dept_id = #{deptId}")
    void deleteBydeptId(Integer deptId);


}
