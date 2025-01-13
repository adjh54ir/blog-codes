package com.blog.springbootwebfluxkafka.controller;

import com.blog.springbootwebfluxkafka.dto.MessageDto;
import com.blog.springbootwebfluxkafka.service.KafkaConsumerService;
import com.blog.springbootwebfluxkafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : KafkaController
 * @since : 2025. 1. 13.
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final KafkaProducerService producerService;
    private final KafkaConsumerService consumerService;

    public KafkaController(KafkaProducerService producerService, KafkaConsumerService consumerService) {
        this.producerService = producerService;
        this.consumerService = consumerService;
    }

    @PostMapping("/publish")
    public Mono<Void> publishMessage(@RequestBody MessageDto message) {
        return Mono.fromRunnable(() -> producerService.sendMessage("test-topic", message));
    }

    @GetMapping("/subscribe")
    public Flux<Object> subscribeMessages() {
        return consumerService.consumeMessages();
    }

}
