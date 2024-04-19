package com.itstudy.projectdemo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//用来测试jwt令牌的生成以及解码
/*@SpringBootTest*/
class ProjectdemoApplicationTests {

    @Test
    void contextLoads() {
    }
    /*
    生成jwt
     */
    @Test
    public void testGenJwt(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","huangchao");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"itstudy")//签名算法
                .setClaims(claims)//自定义的内容（载荷）
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))//设置有效期1h
                .compact();//返回
        System.out.println(jwt);
    }
    /*
    解码jwt
     */
    @Test
    public void  testParseJwt(){
       Claims claims =  Jwts.parser()
                .setSigningKey("itstudy")
                .parseClaimsJws("")
                .getBody();
       System.out.println(claims);
    }
}
