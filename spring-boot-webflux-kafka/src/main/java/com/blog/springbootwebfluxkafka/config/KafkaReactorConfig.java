package com.blog.springbootwebfluxkafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.sender.SenderOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Kafka 연결 설정을 관리하는 관리파일.
 *
 * @author : leejonghoon
 * @fileName : KafkaReactorConfig
 * @since : 2025. 1. 13.
 */
@Configuration
public class KafkaReactorConfig {

    // Kafka 연결 서버 주소
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Reactor 환경에서 Kafka 생성자(Producer) 환경을 구성합니다.
     *
     * @return
     */
    @Bean
    public ReactiveKafkaProducerTemplate<String, String> reactiveKafkaProducerTemplate() {
        Map<String, Object> reactorProducerConfigs = new HashMap<>();
        reactorProducerConfigs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        reactorProducerConfigs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        reactorProducerConfigs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new ReactiveKafkaProducerTemplate<>(SenderOptions.create(reactorProducerConfigs));
    }

    /**
     * Reactor 환경에서 Kafka 소비자(Consumer) 환경을 구성합니다.
     *
     * @return
     */
    @Bean
    public ReactiveKafkaConsumerTemplate<String, Object> reactiveKafkaConsumerTemplate() {
        Map<String, Object> reactorConsumerConfigs = new HashMap<>();
        reactorConsumerConfigs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        reactorConsumerConfigs.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
        reactorConsumerConfigs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        reactorConsumerConfigs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        ReceiverOptions receiverOptions = ReceiverOptions.create(reactorConsumerConfigs).subscription(Collections.singletonList("topic-name"));
        return new ReactiveKafkaConsumerTemplate<>(receiverOptions);
    }

}
