package com.kekwy.mcolc.config;

import com.kekwy.mcolc.filter.Oauth2AuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/*
           _____                      _ __        ______            _____
          / ___/___  _______  _______(_) /___  __/ ____/___  ____  / __(_)___ _
          \__ \/ _ \/ ___/ / / / ___/ / __/ / / / /   / __ \/ __ \/ /_/ / __ `/
         ___/ /  __/ /__/ /_/ / /  / / /_/ /_/ / /___/ /_/ / / / / __/ / /_/ /
        /____/\___/\___/\__,_/_/  /_/\__/\__, /\____/\____/_/ /_/_/ /_/\__, /
                                        /____/                        /____/
 */

@SuppressWarnings({"Convert2MethodRef", "CodeBlock2Expr"})
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, Oauth2AuthenticationTokenFilter filter)
            throws Exception {
        return http
                .authorizeHttpRequests(configurer -> {
                    configurer
                            .requestMatchers(
                                    "/hello",
                                    "/auth/**",
                                    "/css/**",
                                    "/js/**",
                                    "/index.html",
                                    "favicon.ico",
                                    "/doc.html",
                                    "/webjars/**",
                                    "/swagger-resources/**",
                                    "/v3/api-docs/**",
                                    "/item/**"
                            ).permitAll()
                            // 除了上面，所有请求都要拦截
                            .anyRequest().authenticated();
                }).logout(configurer -> {
                    configurer.disable();
                }).requestCache(configurer -> {
                    configurer.disable();
                })
//                .formLogin(configurer -> {
//                    configurer.loginPage("/login");
//                })
//                .oauth2Login(configurer -> {
////                    configurer.loginPage("/login");
//                })
//                .logout(configurer -> {
//                    configurer
//                            .logoutSuccessUrl("/");
//                })
                // 添加自定义认证过滤器
                // TODO: 2024/4/30 处理皮肤站登录的情况
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


}
