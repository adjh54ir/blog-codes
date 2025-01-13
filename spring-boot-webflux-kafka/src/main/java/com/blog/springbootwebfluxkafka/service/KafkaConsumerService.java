package com.blog.springbootwebfluxkafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.ArrayList;
import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : KafkaConsumerService
 * @since : 2025. 1. 13.
 */
@Service
public class KafkaConsumerService {
    private final List<FluxSink<Object>> sinks = new ArrayList<>();

    public Flux<Object> consumeMessages() {
        return Flux.create(sinks::add);
    }

    @KafkaListener(topics = "test-topic", groupId = "group_id")
    public void listen(Object message) {
        sinks.forEach(sink -> sink.next(message));
    }
}
