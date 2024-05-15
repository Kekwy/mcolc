package com.kekwy.mcolc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.kekwy.mcolc.mapper")
@EnableTransactionManagement
public class DbMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbMqApplication.class, args);
    }

}
