package com.blog.springbootkeycloak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients // FeignClient 사용을 선언합니다.
@ConfigurationPropertiesScan("com.blog.springbootkeycloak.dto.properties")
@SpringBootApplication
public class SpringBootKeycloakApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootKeycloakApplication.class, args);
    }

}
