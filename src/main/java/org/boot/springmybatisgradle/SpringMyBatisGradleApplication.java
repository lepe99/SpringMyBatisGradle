package org.boot.springmybatisgradle;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"data.*", "naver.*"})
@MapperScan("data.mapper")
public class SpringMyBatisGradleApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SpringMyBatisGradleApplication.class, args);
    }
    
}
