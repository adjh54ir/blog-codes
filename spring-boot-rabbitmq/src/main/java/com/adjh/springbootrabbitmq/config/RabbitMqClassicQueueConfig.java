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
     * =================================================================================================================
     * ========================================== 예시 1 : 클래식 큐를 이용한 방식 ============================================
     * =================================================================================================================
     */
    /**
     * Queue 구성 : 일반적인 클래식 큐로 구성
     *
     * @return
     */
    @Bean
    public Queue classicQueue1() {
        return QueueBuilder
                // 1. 큐 이름 지정
                .durable("classicQueue1")
                // 2. 데드 레터 익스체인지 지정
                .deadLetterExchange("deadLetterExchange")
                // 3. 데드 레터 라우터 지정
                .deadLetterRoutingKey("deadLetter")
                .build();
    }

    /**
     * Queue 구성 : 일반적인 클래식 큐로 구성
     *
     * @return
     */
    @Bean
    public Queue classicQueue2() {
        return QueueBuilder
                // 1. 큐 이름 지정
                .durable("classicQueue2")
                // 2. 데드 레터 익스체인지 지정
                .deadLetterExchange("deadLetterExchange")
                // 3. 데드 레터 라우터 지정
                .deadLetterRoutingKey("deadLetter")
                .build();
    }


    /**
     * Fanout Exchange 구성
     *
     * @return
     */
    @Bean
    public FanoutExchange classicQueueFanoutExchange() {
        return ExchangeBuilder.fanoutExchange("exchange.fanout.classicQueue").build();
    }

    /**
     * Fanout fanoutExchange - classicQueue1 간의 바인딩을 수행합니다.
     *
     * @param classicQueueFanoutExchange
     * @param classicQueue1
     * @returnㅈ
     */
    @Bean
    Binding classQueueBind1(FanoutExchange classicQueueFanoutExchange, Queue classicQueue1) {
        return BindingBuilder
                .bind(classicQueue1)
                .to(classicQueueFanoutExchange);
    }

    /**
     * TFanout fanoutExchange - classicQueue2 간의 바인딩을 수행합니다.
     *
     * @param classicQueueFanoutExchange
     * @param classicQueue2
     * @returnㅈ
     */
    @Bean
    Binding classQueueBind2(FanoutExchange classicQueueFanoutExchange, Queue classicQueue2) {
        return BindingBuilder
                .bind(classicQueue2)
                .to(classicQueueFanoutExchange);
    }






}