package com.itstudy.springbootmybatisstudy.mapper;

import com.itstudy.springbootmybatisstudy.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper//在运行的时候，会自动生成该接口的实现类对象（代理对象），并且将该对象交给IoC容器管理
public interface UserMapper {
    @Select("select * from user")
    public List<User> list();

/*    @Delete("delete from user where id = #{id}")
    public int delete(Integer id);

   // @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into user(name, age, gender, phone)\n"
            +"values (#{name},#{age},#{gender},#{phone})")
    public void insert(User user);

    @Update("update user set name = #{name},age = #{age},gender = #{gender},phone = #{phone} where id = #{id}")
    public void update(User user);
    @Select("select * from user where id = #{}")
    public User getById(Integer id);

 */
// @Select("select * from user where id = #{id}")
//    public User getById(Integer id);

    //使用Results解决字段名字和属性名不一样的问题，重命名也可以，如id ide
    //最常使用的就是开启mybatis的驼峰命名自动映射开关,使用时，数据库必须是下划线分割，属性名必须是驼峰命名
//    @Results({
//            @Result(column = "字段名字",property = "属性名"),
//              @Result(column = "字段名字",property = "属性名"),
//    })
     @Select("select * from user where id = #{id}")//注解写法
      public User getById(Integer id);
/*     使用 concat('%',#{name},'%')，是为了解决#{name}不能在''之内的问题
     @Select("select * from user where name like  concat('%',#{name},'%') and gender = #{gender}")
    public List<User> getUser(String name,Short gender);
    下面是使用xml配置文件的方式进行数据库操作
    添加@Select()之类的就是使用注解进行操作数据库数据，使用xml文件也是操作数据库数据的方式之一并且我觉得更加重要*/
     public List<User> getUser(String name,Short gender);//xml写法
    //单条操作
     public void insertUser(User user);
     public void updateUser(User user);
     public void deleteUser(User user);
     //批量操作
     public void deleteUsers(List<Integer> ids);
}
