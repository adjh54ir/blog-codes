package com.adjh.springbootkafkaconsumer.component;

import com.adjh.springbootkafkaconsumer.service.KafkaConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka 생산자(Producer)로 부터 생성된 Topic 내의 메시지를 수신합니다
 *
 * @author : jonghoon
 * @fileName : KafkaConsumerComponent
 * @since : 25. 1. 9.
 */
@Slf4j
@Component
public class KafkaConsumerComponent {


    private final KafkaConsumerService kafkaConsumerService;

    public KafkaConsumerComponent(KafkaConsumerService kafkaConsumerService) {
        this.kafkaConsumerService = kafkaConsumerService;
    }

    /**
     * 기본적인 토픽 리스닝
     *
     * @param message 수신 메시지
     */
    @KafkaListener(topics = "test-topic-1")
    public void listen(String message) {
        try {
            log.info("메시지 수신: {}", message);
            // 비즈니스 로직 처리
            String processedMessage = kafkaConsumerService.processMessage(message);
            log.info("메시지 처리 완료: {}", processedMessage);

        } catch (Exception e) {
            log.error("메시지 처리 중 오류 발생: {}", e.getMessage(), e);
        }
    }

}
