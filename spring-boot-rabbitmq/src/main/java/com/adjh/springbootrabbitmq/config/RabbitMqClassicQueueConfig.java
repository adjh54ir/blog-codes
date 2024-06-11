package com.adjh.springbootrabbitmq.config;

import com.adjh.springbootrabbitmq.model.constant.ConstQueueAttr;
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
    public Queue classicQueue1() {
        return QueueBuilder
                // 1. 큐 유지 여부 설정
                .durable("classicQueue1")
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
     * Queue 구성 : 일반적인 클래식 큐로 구성
     * TTL, 우선순위, 데드레터 익스체인지, 데드레터 라우트 키, 최대 길이 지정
     *
     * @return
     */
    @Bean
    public Queue classicQueue2() {
        return QueueBuilder
                // 1. 큐 유지 여부 설정
                .durable("classicQueue2")
                // 1. 메시지의 Expiration 지정(TTL : 1초)
                .withArgument(ConstQueueAttr.QUEUE_TTL, 150000)                                       // or .ttl()
                // 2. 큐 우선순위 지정
                .withArgument(ConstQueueAttr.QUEUE_PRIORITY, 2)                                     // or .maxPriority()
                // 3. 데드 레터 익스체인지 지정
                .withArgument(ConstQueueAttr.QUEUE_DEAD_LETTER_EXCHANGE, "deadLetterExchange")      // or .deadLetterExchange()
                // 4. 데드 레터 라우터 지정
                .withArgument(ConstQueueAttr.QUEUE_DEAD_LETTER_ROUTE_KEY, "deadLetter")             // or .deadLetterRoutingKey()
                // 5. Queue의 길이 지정
                .withArgument(ConstQueueAttr.QUEUE_MAX_LENGTH, 100)                                 // or .maxLength()
                .build();
    }

    /**
     * Topic Exchange 구성
     *
     * @return
     */
    @Bean
    public TopicExchange classicQueueTopicExchange() {
        return new TopicExchange("exchange.topic.classicQueue");
    }

    /**
     * Topic Exchange 와 Queue1 간의 바인딩을 수행합니다.
     *
     * @param classicQueueTopicExchange
     * @param classicQueue1
     * @return
     */
    @Bean
    Binding classQueueBind1(TopicExchange classicQueueTopicExchange, Queue classicQueue1) {
        return BindingBuilder
                .bind(classicQueue1)
                .to(classicQueueTopicExchange)
                .with("classic.*");     // 라우팅 키 (Routing key)
    }

    /**
     * Topic Exchange 와 Queue1 간의 바인딩을 수행합니다.
     *
     * @param classicQueueTopicExchange
     * @param classicQueue2
     * @return
     */
    @Bean
    Binding classQueueBind2(TopicExchange classicQueueTopicExchange, Queue classicQueue2) {
        return BindingBuilder
                .bind(classicQueue2)
                .to(classicQueueTopicExchange)
                .with("classic.*");     // 라우팅 키 (Routing key)
    }
}