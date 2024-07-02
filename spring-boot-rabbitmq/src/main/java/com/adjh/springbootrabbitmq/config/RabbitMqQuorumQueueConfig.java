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

    /**
     * Quorum Queue 구성 : 쿼럼 큐 형태로 구성
     *
     * @return
     */
    @Bean
    public Queue quorumQueue() {
        return QueueBuilder
                // 1. 큐 유지 여부 설정
                .durable("quorumQueue")
                .quorum()
                // 2. 데드 레터 익스체인지 지정
                .deadLetterExchange("deadLetterExchange")
                // 3. 데드 레터 라우터 지정
                .deadLetterRoutingKey("deadLetter")
                .build();
    }

    /**
     * Direct Exchange 구성
     *
     * @return
     */
    @Bean
    DirectExchange quorumQueueDirectExchange() {
        // direct.exchange 이름의 Direct Exchange 구성
        return new DirectExchange("exchange.direct.quorumQueue");
    }

    /**
     * Direct Exchange 와 Queue1 간의 바인딩을 수행합니다.
     * - Direct Exchange 방식으로 Queue1와 라우팅 키(Routing key)를 기반으로 바인딩 수행.
     *
     * @param quorumQueueDirectExchange
     * @param quorumQueue
     * @return
     */
    @Bean
    Binding classQueueBind(DirectExchange quorumQueueDirectExchange, Queue quorumQueue) {
        return BindingBuilder
                .bind(quorumQueue)
                .to(quorumQueueDirectExchange)
                .with("quorumQueue");     // 라우팅 키 (Routing key)
    }
}
