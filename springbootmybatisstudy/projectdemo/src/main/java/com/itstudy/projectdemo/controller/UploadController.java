package com.itstudy.projectdemo.controller;

import com.itstudy.projectdemo.pojo.Result;
import com.itstudy.projectdemo.util.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
public class UploadController {
    @Autowired
    private AliOSSUtils aliOSSUtils;
    /*//本地存储文件
    @PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile image) throws IOException {
        log.info("文件上传的数据：{}，{}，{}",username,age,image);

        //获取文件名字
        String OriginalFilename =  image.getOriginalFilename();

        //构建唯一的文件名字（不能够重复），使用uuid(通用唯一识别码)
        int index = OriginalFilename.lastIndexOf(".");
        String extname = OriginalFilename.substring(index);
        String newfilename = UUID.randomUUID().toString() + extname;
        log.info("新的文件名；{}",newfilename);

        //将文件储存在服务器的磁盘目录上面 E:\idea_study，不常用
        image.transferTo(new File("E:\\idea_study\\"+newfilename));
        return Result.success();
    }*/
@PostMapping("/upload")
    public Result upload(MultipartFile image) throws IOException {
    log.info("文件上传 文件名：{}",image.getOriginalFilename());
    String url = AliOSSUtils.upload(image);
    log.info("文件上传完成，文件访问的地址：{}",url);
    return Result.success(url);
    }
}
