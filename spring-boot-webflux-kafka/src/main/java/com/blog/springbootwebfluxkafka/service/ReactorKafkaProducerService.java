package com.blog.springbootwebfluxkafka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

/**
 * Reactor 기반의 Kafka 생성자(Producer) 비즈니스 로직 처리
 *
 * @author : leejonghoon
 * @fileName : ReactorKafkaProducerService
 * @since : 2025. 1. 13.
 */
@Slf4j
@Service
public class ReactorKafkaProducerService {

    private final ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate;

    public ReactorKafkaProducerService(ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate) {
        this.reactiveKafkaProducerTemplate = reactiveKafkaProducerTemplate;
    }

    /**
     * Kafka 토픽에 메시지를 전송합니다.
     *
     * @param topic   메시지를 전송할 토픽
     * @param message 전송할 메시지
     * @return Mono<SenderResult < Void>> 전송 결과
     */
    public Mono<SenderResult<Void>> sendMessage(String topic, String message) {
        return reactiveKafkaProducerTemplate.send(topic, message)
                .doOnSuccess(senderResult ->
                        log.info("메시지 전송 성공 - topic: {}, message: {}", topic, message))
                .doOnError(error ->
                        log.error("메시지 전송 실패 - topic: {}, message: {}, error: {}",
                                topic, message, error.getMessage()));
    }

    /**
     * Kafka 토픽에 키와 함께 메시지를 전송합니다.
     *
     * @param topic   메시지를 전송할 토픽
     * @param key     메시지 키
     * @param message 전송할 메시지
     * @return Mono<SenderResult < Void>> 전송 결과
     */
    public Mono<SenderResult<Void>> sendMessage(String topic, String key, String message) {
        return reactiveKafkaProducerTemplate.send(topic, key, message)
                .doOnSuccess(senderResult ->
                        log.info("메시지 전송 성공 - topic: {}, key: {}, message: {}",
                                topic, key, message))
                .doOnError(error ->
                        log.error("메시지 전송 실패 - topic: {}, key: {}, message: {}, error: {}",
                                topic, key, message, error.getMessage()));
    }

    /**
     * 기본 토픽으로 메시지를 전송합니다.
     *
     * @param message 전송할 메시지
     * @return Mono<SenderResult < Void>> 전송 결과
     */
    public Mono<SenderResult<Void>> sendDefaultMessage(String message) {
        String defaultTopic = "your-default-topic-name"; // 기본 토픽 이름 설정
        return reactiveKafkaProducerTemplate.send(defaultTopic, message)
                .doOnSuccess(senderResult ->
                        log.info("기본 토픽으로 메시지 전송 성공 - message: {}", message))
                .doOnError(error ->
                        log.error("기본 토픽으로 메시지 전송 실패 - message: {}, error: {}",
                                message, error.getMessage()));
    }
}
