package com.blog.springbootwebfluxkafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : KafkaProducerService
 * @since : 2025. 1. 13.
 */
@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, Object message) {
        kafkaTemplate.send(topic, message);
    }
}
