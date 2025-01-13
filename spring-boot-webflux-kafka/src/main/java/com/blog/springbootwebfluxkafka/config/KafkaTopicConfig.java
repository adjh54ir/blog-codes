package com.blog.springbootwebfluxkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Kafka Topic을 생성하는 설정 파일입니다.
 *
 * @author : jonghoon
 * @fileName : KafkaTopicConfig
 * @since : 25. 1. 10.
 */
@Configuration
public class KafkaTopicConfig {

    /**
     * Topic 구성 예시
     *
     * @return NewTopic
     */
    @Bean
    public NewTopic exampleTopic() {
        return TopicBuilder.name("test-topic-1")
                .partitions(3)                          // 파티션 수 설정
                .replicas(1)                             // 복제 팩터 설정 (1)
                .config(                                            // 추가 설정
                        TopicConfig.RETENTION_MS_CONFIG,
                        String.valueOf(7 * 24 * 60 * 60 * 1000L)  // 7일
                )
                .build();
    }

    /**
     * Topic 구성 예시
     *
     * @return NewTopic
     */
    @Bean
    public NewTopic compactTopic() {
        return TopicBuilder.name("test-topic-2")
                .partitions(1)
                .replicas(1)
                .compact()                         // 압축 정책 설정
                .build();
    }
}