package com.adjh.springbootkafka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * kafkaTemplate 활용한 Topic 내에 메시지를 전송하는 다양한 방법
 *
 * @author : jonghoon
 * @fileName : KafkaProducerService
 * @since : 25. 1. 10.
 */
@Slf4j
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topicName = "test-topic-1";

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * 기본적인 메시지 전송
     *
     * @param message 전송 메시지
     */
    public void sendMessage(String message) {
        kafkaTemplate.send(topicName, message);
    }

    /**
     * 키와 함께 메시지 전송
     *
     * @param key     Topic Key
     * @param message 전송 메시지
     */
    public void sendMessageWithKey(String key, String message) {
        kafkaTemplate.send(topicName, key, message);
    }

    /**
     * 특정 파티션으로 메시지 전송
     *
     * @param message   전송 메시지
     * @param partition 파티션 명
     */
    public void sendMessageToPartition(String message, int partition) {
        kafkaTemplate.send(topicName, partition, null, message);
    }


    /**
     * 비동기 전송 결과 처리
     *
     * @param message 전송 메시지
     */
    public void sendMessageWithCallback(String message) {
        kafkaTemplate.send(topicName, message)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.debug("Success: {} ", result.getRecordMetadata());
                    } else {
                        log.error("Failed: {}", ex.getMessage());
                    }
                });
    }
}
