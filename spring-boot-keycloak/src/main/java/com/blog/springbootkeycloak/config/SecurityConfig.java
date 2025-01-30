package com.blog.springbootkeycloak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : SecurityConfig
 * @since : 25. 1. 30.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)                                                          // CSRF 보호 비활성화
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())                                   // 우선 모든 요청에 대한 허용
                .oauth2ResourceServer()
                .jwt();

        return http.build();
    }
}
