package com.blog.springbootwebfluxkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class SpringBootWebfluxKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebfluxKafkaApplication.class, args);
    }

}
