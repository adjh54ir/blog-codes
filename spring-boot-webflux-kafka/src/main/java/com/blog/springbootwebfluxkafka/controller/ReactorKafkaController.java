package com.blog.springbootwebfluxkafka.controller;

import com.blog.springbootwebfluxkafka.service.ReactorKafkaConsumerService;
import com.blog.springbootwebfluxkafka.service.ReactorKafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : ReactiveKafkaController
 * @since : 2025. 1. 13.
 */
@Slf4j
@RestController
public class ReactorKafkaController {

    private final ReactorKafkaProducerService producer;
    private final ReactorKafkaConsumerService consumer;

    public ReactorKafkaController(ReactorKafkaProducerService producer, ReactorKafkaConsumerService consumer) {
        this.producer = producer;
        this.consumer = consumer;
    }

    @PostMapping("/send")
    public Mono<ResponseEntity<String>> sendMessage(@RequestBody String message) {
        return producer.send("your-topic", message)
                .map(result -> ResponseEntity.ok("Message sent successfully"))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(500)
                        .body("Failed to send message: " + e.getMessage())));
    }

    @GetMapping("/stream")
    public Flux<ServerSentEvent<String>> streamMessages() {
        return consumer.consume()
                .map(message -> ServerSentEvent.<String>builder()
                        .data(message)
                        .build());
    }
}
