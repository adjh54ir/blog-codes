package com.adjh.springbootkafkaconsumer.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : KafkaErrorHandler
 * @since : 25. 1. 10.
 */
@Slf4j
@Configuration
public class KafkaErrorHandler {
    /**
     * KafkaListenerErrorHandler 로 공통으로 사용하는 에러 핸들러를 구성합니다.
     *
     * @return
     */
    @Bean
    public KafkaListenerErrorHandler customErrorHandler() {
        return (message, exception) -> {
            log.error("메시지 처리 중 오류 발생: {}", exception.getMessage());
            log.error("문제가 발생한 메시지: {}", message.getPayload());
            // 예외 처리 로직 구현
            // 필요한 경우 다른 결과 반환 가능
            return "Error Handled";
        };
    }
}
