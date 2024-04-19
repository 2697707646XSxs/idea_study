package com.aliyun.oss;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(AliOssProject.class)
public class AliOssAutoConfiguration {
    @Bean
    public AliOssUtils aliOssUtils(AliOssProject aliOssProject){

        AliOssUtils aliOssUtils = new AliOssUtils();
        aliOssUtils.setAliOssproject(aliOssProject);
        return aliOssUtils;
    }
}
