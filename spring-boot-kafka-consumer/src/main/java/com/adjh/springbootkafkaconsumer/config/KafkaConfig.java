package com.adjh.springbootkafkaconsumer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

/**
 * Kafka 연결 설정을 관리하는 관리파일.
 *
 * @author : jonghoon
 * @fileName : KafkaConfig
 * @since : 25. 1. 9.
 */
@Slf4j
@Configuration
public class KafkaConfig {

    // Kafka 연결 서버 주소
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Consumer 설정을 위한 Factory Bean
     * 메시지 소비자의 역직렬화 설정 및 그룹 ID, 오프셋 설정을 담당
     *
     * @return ConsumerFactory<String, String>
     */
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    /**
     * Kafka Listener 컨테이너 Factory Bean
     * 어노테이션 @KafkaListener 메서드들을 위한 컨테이너 설정
     *
     * @return ConcurrentKafkaListenerContainerFactory<String, String>
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

//    /**
//     * DefaultErrorHandler 구성
//     *
//     * @return
//     */
//    @Bean
//    public DefaultErrorHandler defaultErrorHandler() {
//        // 재시도 정책 설정
//        BackOff backOff = new FixedBackOff(1000L, 2);
//
//        DefaultErrorHandler errorHandler = new DefaultErrorHandler((consumerRecord, exception) -> {
//            // 최종 실패 시 처리할 로직
//            log.debug("처리 실패한 레코드: {} exception : {} ", consumerRecord, exception);
//        }, backOff);
//
//        // 특정 예외는 재시도하지 않도록 설정
//        errorHandler.addNotRetryableExceptions(IllegalArgumentException.class);
//        return errorHandler;
//    }


}