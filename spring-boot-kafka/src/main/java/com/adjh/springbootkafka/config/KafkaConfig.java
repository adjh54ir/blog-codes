package com.adjh.springbootkafka.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.*;

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
     * Producer 설정을 위한 Factory Bean
     * - 메시지 생산자의 직렬화 설정 및 서버 연결 설정을 담당합니다.
     *
     * @return ProducerFactory<String, String>
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }


    /**
     * Kafka 메시지를 전송하기 위한 템플릿 Bean
     * 실제 애플리케이션에서 메시지 전송시 사용됨
     *
     * @return KafkaTemplate<String, String>
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}