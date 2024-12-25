package com.adjh.springbootoauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
@ConfigurationPropertiesScan("com.adjh.springbootoauth2.config.properties")
public class SpringBoot3SecurityOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3SecurityOauth2Application.class, args);
    }

}
