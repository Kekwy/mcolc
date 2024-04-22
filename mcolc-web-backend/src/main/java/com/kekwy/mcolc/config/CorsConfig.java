package com.kekwy.mcolc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.addAllowedOrigin("http://localhost:8080");//允许哪些网站跨域请求 "*"允许所有网站
//        configuration.addAllowedHeader("*");//允许在请求头中携带的头信息
//        configuration.addAllowedMethod("*");//允许跨域的ajax的请求方式"GET" "POST" "DELETE" "PUT" "OPTIONS"
//        configuration.setMaxAge(3600L);//本次跨域检测的有效期
//        configuration.setAllowCredentials(true);//是否允许携带cookie
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return new CorsFilter(source);
//    }
}
