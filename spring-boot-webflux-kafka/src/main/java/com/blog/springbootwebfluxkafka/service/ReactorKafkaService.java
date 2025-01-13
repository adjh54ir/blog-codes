package com.blog.springbootwebfluxkafka.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : ReactiveKafkaService
 * @since : 2025. 1. 13.
 */
@Slf4j
@Service
public class ReactorKafkaService {
    private final ReactiveKafkaProducerTemplate<String, String> producerTemplate;
    private final ReactiveKafkaConsumerTemplate<String, String> consumerTemplate;


    public ReactorKafkaService(
            ReactiveKafkaProducerTemplate<String, String> producerTemplate,
            ReactiveKafkaConsumerTemplate<String, String> consumerTemplate) {
        this.producerTemplate = producerTemplate;
        this.consumerTemplate = consumerTemplate;
    }

    public Mono<SenderResult<Void>> sendMessage(String topic, String message) {
        return producerTemplate.send(topic, message)
                .doOnSuccess(result -> log.info("Message sent successfully"))
                .doOnError(error -> log.error("Message send failed", error));
    }

    public Flux<String> consumeMessages(String topic) {
        return consumerTemplate.receiveAutoAck()
                .map(ConsumerRecord::value)
                .doOnNext(message -> log.info("Received message: {}", message));
    }
}
