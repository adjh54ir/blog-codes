package com.adjh.springbootkafka.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : ProducerComponent
 * @since : 25. 1. 9.
 */
@Component
public class ProducerComponent {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerComponent(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("topicName", message);
    }
}
