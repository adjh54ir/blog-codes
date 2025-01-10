package com.adjh.springbootkafka.component;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : ConsumerComponent
 * @since : 25. 1. 9.
 */
@Component
public class ConsumerComponent {

    @KafkaListener(topics = "topic", groupId = "group_1")
    public void listen(String message) {
        System.out.println(message);
    }
}
