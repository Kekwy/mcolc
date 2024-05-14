package com.kekwy.mcolc;

import com.kekwy.mcolc.config.McolcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(McolcConfig.class)
public class McolcApplication {

    public static void main(String[] args) {
        SpringApplication.run(McolcApplication.class, args);
    }

}
