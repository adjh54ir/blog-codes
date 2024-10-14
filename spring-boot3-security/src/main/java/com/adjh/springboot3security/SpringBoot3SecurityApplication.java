package com.adjh.springboot3security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
public class SpringBoot3SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3SecurityApplication.class, args);
    }

}
