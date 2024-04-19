package com.itstudy.springbootmybatisstudy;

import com.itstudy.springbootmybatisstudy.mapper.UserMapper;
import com.itstudy.springbootmybatisstudy.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest//SpringBoot整合单元测试的注解
class SpringbootMybatisStudyApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test() {
        List<User> userList = userMapper.list();
        userList.forEach(System.out::println);
    }
/*    @Test
//接口方法没实现好的就不要写出来，不然嘎嘎报错
    public void test() {
        List<User> userList = userMapper.list();
        userList.forEach(System.out::println);
    }

    @Test
    public void textdelete() {
        int delete = userMapper.delete(4);
        System.out.println(delete);
        userMapper.delete(5);
    }

    @Test
    public void textinset() {
        User user = new User();
        user.setName("tom");//自己创建一个实体类，加入数据，然后再传入接口
        user.setPassword("13531987523");
                //其他没写出来
    }

    @Test
    public void textupdate() {
        User user = new User();
        user.setName("s");
    }

    @Test
    public void textselect1(){
       User user =  userMapper.getById(1);
        System.out.println(user);
    }
 */
    @Test
    public void textget(){
        User user =  userMapper.getById(1);
        System.out.println(user);
    }
    @Test
    public void getUser(){
        List<User> userList = userMapper.getUser("王",(short)1);
        System.out.println(userList);
    }
    @Test
    public void insertUser(){
        User user = new User();
        user.setName("黄超");
        user.setAge((short)21);
        user.setGender((short)1);
        user.setPhone(null);
        userMapper.insertUser(user);
        System.out.println(user);
    }
    @Test
    public void updateUser(){
        User user = new User();
        user.setId(7);
       // user.setPhone("15914934907");
        user.setGender((short)2);
        userMapper.updateUser(user);
        System.out.println(user);
    }
    @Test
    public void deleteUser(){//单条的删除
        User user = new User();
        user.setId(8);
        userMapper.deleteUser(user);
        System.out.println(user);
    }
    @Test
    public void deleteUsers(){//批量的删除
        List<Integer> ids = Arrays.asList(6,7);
        userMapper.deleteUsers(ids);
        ids.forEach(System.out::println);
    }
}