package com.itstudy.springbootmybatisstudy.pojo;

import lombok.*;

/*@Getter   get方法
@Setter  set方法
@ToString   tostring方法
@EqualsAndHashCode  重写方法  */
//@Data/*包含以上四个注解的功能*/
@Data
@NoArgsConstructor //无参构造方法
@AllArgsConstructor //有参构造方法
public class User {
    private Integer id;
    private String name;
    private Short age;
    private Short gender;
    private String phone;
}
    /*当类的属性名和数据库表中的字段名不一样时，不能自动封装*/
/*
    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getAge() {
        return age;
    }

    public void setAge(short age) {
        this.age = age;
    }

    public short getGender() {
        return gender;
    }

    public void setGender(short gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                '}';
    }*/