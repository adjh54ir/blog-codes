package com.adjh.springboot4init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.service.registry.ImportHttpServices;

@ImportHttpServices(basePackages = "com.adjh.springboot4init.service.client")
@SpringBootApplication
public class SpringBoot4InitApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot4InitApplication.class, args);
    }

}
