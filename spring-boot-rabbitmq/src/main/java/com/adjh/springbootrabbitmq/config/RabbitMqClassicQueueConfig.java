package com.adjh.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 클래식 큐(Classic Queue)를 구성하기 위한 설정 클래스입니다.
 *
 * @author : lee
 * @fileName : RabbitMqClassicQueueConfig
 * @since : 24. 6. 10.
 */
@Configuration
public class RabbitMqClassicQueueConfig {

    /**
     * Queue 구성 : 일반적인 클래식 큐로 구성
     * TTL, 우선순위, 데드레터 익스체인지, 데드레터 라우트 키, 최대 길이 지정
     *
     * @return
     */
    @Bean
    public Queue classicQueue() {
        return QueueBuilder
                // 1. 큐 유지 여부 설정
                .durable("classicQueue")
                // 1. 메시지의 Expiration 지정(TTL : 1초)
                .ttl(1500000)
                // 2. 큐 우선순위 지정
                .maxPriority(2)
                // 3. 데드 레터 익스체인지 지정
                .deadLetterExchange("deadLetterExchange")
                // 4. 데드 레터 라우터 지정
                .deadLetterRoutingKey("deadLetter")
                // 5. Queue의 길이 지정
                .maxLength(100)
                .build();
    }


    /**
     * Topic Exchange 구성
     *
     * @return
     */
    @Bean
    public DirectExchange classicQueueTopicExchange() {
        return ExchangeBuilder.directExchange("exchange.topic.classicQueue").build();
    }

    /**
     * Topic Exchange 와 Queue1 간의 바인딩을 수행합니다.
     *
     * @param classicQueueTopicExchange
     * @param classicQueue
     * @return
     */
    @Bean
    Binding classQueueBind1(DirectExchange classicQueueTopicExchange, Queue classicQueue) {
        return BindingBuilder
                .bind(classicQueue)
                .to(classicQueueTopicExchange)
                .with("classic.classicQueue");     // 라우팅 키 (Routing key)
    }
}