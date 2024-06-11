package com.adjh.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 쿼럼 큐(Quorum Queue)를 구성하기 위한 설정 클래스입니다.
 *
 * @author : lee
 * @fileName : RabbitMqQuorumQueueConfig
 * @since : 24. 6. 11.
 */
@Configuration
public class RabbitMqQuorumQueueConfig {

//    /**
//     * Queue 구성 : 일반적인 클래식 큐로 구성
//     * TTL, 우선순위, 데드레터 익스체인지, 데드레터 라우트 키, 최대 길이 지정
//     *
//     * @return
//     */
//    @Bean
//    public Queue quorumQueue1() {
//        return QueueBuilder
//                // 1. 큐 유지 여부 설정
//                .durable("quorumQueue1")
//                .quorum()
//                // 1. 메시지의 Expiration 지정(TTL : 10초)
//                .ttl(10000)
//                // 3. 데드 레터 익스체인지 지정
//                .deadLetterExchange("deadLetterExchange")
//                // 4. 데드 레터 라우터 지정
//                .deadLetterRoutingKey("deadLetter")
//                // 5. Queue의 길이 지정
//                .maxLength(100)
//                .build();
//    }
//
//    /**
//     * Direct Exchange 구성
//     *
//     * @return
//     */
//    @Bean
//    DirectExchange directExchange1() {
//        // direct.exchange 이름의 Direct Exchange 구성
//        return new DirectExchange("exchange.direct.quorumQueue1");
//    }
//
//    /**
//     * Direct Exchange 와 Queue1 간의 바인딩을 수행합니다.
//     * - Direct Exchange 방식으로 Queue1와 라우팅 키(Routing key)를 기반으로 바인딩 수행.
//     *
//     * @param directExchange1
//     * @param quorumQueue1
//     * @return
//     */
//    @Bean
//    Binding classQueueBind(DirectExchange directExchange1, Queue quorumQueue1) {
//        return BindingBuilder
//                .bind(quorumQueue1)
//                .to(directExchange1)
//                .with("quorum.queue1");     // 라우팅 키 (Routing key)
//    }
}
