package com.kekwy.mcolc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(configurer -> {
                    configurer
                            .requestMatchers("/hello").hasRole("USER")
                            .anyRequest().permitAll();
//                    configurer
                })
                .formLogin(configurer -> {
                    configurer.loginPage("/login");
                })
                .oauth2Login(configurer -> {
//                    configurer.loginPage("/login");
                })
                .logout(configurer -> {
                    configurer
                            .logoutSuccessUrl("/");
                })
                .build();
    }

}
