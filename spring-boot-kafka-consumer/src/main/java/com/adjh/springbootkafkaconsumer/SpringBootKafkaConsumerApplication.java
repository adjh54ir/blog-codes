package com.adjh.springbootkafkaconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class SpringBootKafkaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKafkaConsumerApplication.class, args);
    }

}
