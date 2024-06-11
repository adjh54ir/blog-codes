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
                .ttl(1000)
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
                .withArgument(ConstQueueAttr.QUEUE_TTL, 1000)                                       // or .ttl()
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
     * Direct Exchange 구성 : Dead Letter Exchange로 라우팅을 하는데 사용
     * - 성공적으로 처리하지 못한 메시지를 메시지 큐(deadLetterQueue)로 전달하는 역할을 수행합니다.
     *
     * @return
     */
    @Bean
    public DirectExchange classicQueueDirectExchange() {
        return new DirectExchange("exchange.direct.classicQueue");
    }

    /**
     * Direct Exchange 와 Queue1 간의 바인딩을 수행합니다.
     * - Direct Exchange 방식으로 Queue1와 라우팅 키(Routing key)를 기반으로 바인딩 수행.
     *
     * @param classicQueueDirectExchange
     * @param classicQueue1
     * @return
     */
    @Bean
    Binding classQueueBind1(DirectExchange classicQueueDirectExchange, Queue classicQueue1) {
        return BindingBuilder
                .bind(classicQueue1)
                .to(classicQueueDirectExchange)
                .with("classic.queue1");     // 라우팅 키 (Routing key)
    }

    /**
     * Direct Exchange 와 Queue1 간의 바인딩을 수행합니다.
     * - Direct Exchange 방식으로 Queue1와 라우팅 키(Routing key)를 기반으로 바인딩 수행.
     *
     * @param directExchange
     * @param classicQueue2
     * @return
     */
    @Bean
    Binding classQueueBind2(DirectExchange directExchange, Queue classicQueue2) {
        return BindingBuilder
                .bind(classicQueue2)
                .to(directExchange)
                .with("classic.queue2");     // 라우팅 키 (Routing key)
    }
}
