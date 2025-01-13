package com.blog.springbootwebfluxkafka.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import java.util.Collections;
import java.util.Map;

/**
 * Reactor 기반의 Kafka 소비자(Consumer) 비즈니스 로직 처리
 *
 * @author : leejonghoon
 * @fileName : ReactorKafkaConsumerService
 * @since : 2025. 1. 13.
 */
@Slf4j
@Service
public class ReactorKafkaConsumerService {

    private final ReactiveKafkaConsumerTemplate<String, String> kafkaTemplate;

    public ReactorKafkaConsumerService(ReactiveKafkaConsumerTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Flux<ReceiverRecord<String, String>> consumeMessages(String topic) {
        return kafkaTemplate.receive()
                .doOnNext(record -> {
                    log.info("Received message: {}", record.value());
                    // 메시지 처리 로직
                    record.receiverOffset().acknowledge(); // 수동 커밋
                })
                .doOnError(error -> log.error("Error receiving message", error))
                .doOnCancel(() -> log.info("Consumer cancelled"));
    }
}
