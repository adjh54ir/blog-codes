package com.adjh.springbootrabbitmqconsumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMq의 설정파일입니다.
 *
 * @author : lee
 * @fileName : RabbitMqConfig
 * @since : 24. 5. 28.
 */
@Configuration
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.port}")
    private int port;
}
