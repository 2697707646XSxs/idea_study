package com.itstudy.projectdemo.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
/*
通过将引用改造成类，注入，在需要实现的类上面，减少代码的编写
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")//批量注入，@Value 单个注入
public class AliOSProject {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}
