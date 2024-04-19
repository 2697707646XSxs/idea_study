package com.itstudy.projectdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan//开启了对servlet组建的支持
@SpringBootApplication
public class ProjectdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectdemoApplication.class, args);
    }
}
