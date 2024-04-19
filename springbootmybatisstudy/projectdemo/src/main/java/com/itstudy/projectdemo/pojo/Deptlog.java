package com.itstudy.projectdemo.pojo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Deptlog {
    //id
    private Integer id;
    //操作时间
    private LocalDateTime createTime;
    //操作内容
    private String description;
}
