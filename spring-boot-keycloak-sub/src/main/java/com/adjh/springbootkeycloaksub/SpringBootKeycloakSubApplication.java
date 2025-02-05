package com.adjh.springbootkeycloaksub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients // FeignClient 사용을 선언합니다.
@SpringBootApplication
@ConfigurationPropertiesScan("com.adjh.springbootkeycloaksub.config.properties")
public class SpringBootKeycloakSubApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKeycloakSubApplication.class, args);
    }

}
