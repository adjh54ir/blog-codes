package com.adjh.springbootrabbitmq.service.impl;

import com.adjh.springbootrabbitmq.dto.MessageDto;
import com.adjh.springbootrabbitmq.service.ProducerQueueService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * 다양한 큐를 테스트 해보기 위한 서비스 구현체입니다.
 *
 * @author : lee
 * @fileName : ProducerQueueServiceImpl
 * @since : 24. 6. 10.
 */
@Service
public class ProducerQueueServiceImpl implements ProducerQueueService {

    private final RabbitTemplate rabbitTemplate;

    public ProducerQueueServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Producer ClassQueue 기반의 메시지 전달
     *
     * @param messageDto
     */
    @Override
    public void sendClassicQueue(MessageDto messageDto) {
        try {
            // 1. 전송하려는 객체를 문자열로 변환합니다.
            ObjectMapper objectMapper = new ObjectMapper();
            String objectToJSON = objectMapper.writeValueAsString(messageDto);

            // '*'를 사용하여 'classic.test1' 또는 'classic.test2'를 대체.
            rabbitTemplate.convertAndSend("exchange.topic.classicQueue", "classic.*", objectToJSON);
            // '#'를 사용하여 'classic.test1', 'classic.test2', 'classic.test3' 등을 대체.
            rabbitTemplate.convertAndSend("exchange.topic.classicQueue", "classic.#", objectToJSON);
        } catch (JsonProcessingException jpe) {
            System.out.println("파싱 오류 발생");
        }
    }

    /**
     * Producer QuorumQueue 기반의 메시지 전달
     *
     * @param messageDto
     */
    @Override
    public void sendQuorumQueue(MessageDto messageDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String objectToJSON = objectMapper.writeValueAsString(messageDto);
        } catch (JsonProcessingException jpe) {
            System.out.println("파싱 오류 발생");
        }


    }
}
