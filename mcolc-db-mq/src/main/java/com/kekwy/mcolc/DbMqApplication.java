package com.kekwy.mcolc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kekwy.mcolc.mapper")
public class DbMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbMqApplication.class, args);
    }

}
