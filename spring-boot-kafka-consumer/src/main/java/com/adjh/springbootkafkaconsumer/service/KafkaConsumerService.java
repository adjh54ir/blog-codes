package com.adjh.springbootkafkaconsumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Kafka Listener 를 통해서 전달 받은 메시지를 비즈니스 로직 처리
 *
 * @author : jonghoon
 * @fileName : KafkaConsumerService
 * @since : 25. 1. 10.
 */
@Slf4j
@Service
public class KafkaConsumerService {

    /**
     * 간단한 비즈니스 로직 처리
     *
     * @param message 리스너로 들어온 메시지
     * @return
     */
    public String processMessage(String message) {
        try {
            // 비즈니스 로직 처리 : 메시지를 대문자로 변환하여 이를 반환합니다.
            return message.toUpperCase();
        } catch (Exception e) {
            log.error("메시지 처리 중 오류 발생: {}", e.getMessage(), e);
            throw new RuntimeException("메시지 처리 실패", e);
        }
    }

}
