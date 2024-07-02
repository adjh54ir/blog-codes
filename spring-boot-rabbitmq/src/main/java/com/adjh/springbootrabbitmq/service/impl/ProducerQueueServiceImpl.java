package com.adjh.springbootrabbitmq.service.impl;

import com.adjh.springbootrabbitmq.dto.MessageDto;
import com.adjh.springbootrabbitmq.service.ProducerQueueService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            rabbitTemplate.convertAndSend("exchange.fanout.classicQueue", "", objectToJSON);
        } catch (JsonProcessingException jpe) {
            System.out.println("파싱 오류 발생");
        }
    }


    /**
     * 우선순위가 5인 메시지 전송
     *
     * @param messageDto
     */
    @Override
    @Transactional(readOnly = true)
    public void sendPriority5Queue(MessageDto messageDto) {

        // [STEP1] MessageProperties 인스턴스 구성
        MessageProperties mpb = MessagePropertiesBuilder.newInstance()
                .setHeader("x-max-priority", 5)
                .build();


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // [STEP2] 메시지 객체 직렬화 수행
            String objectToJSON = objectMapper.writeValueAsString(messageDto);

            // [STEP3] 직렬화 객체와 메시지 정보로 메시지 객체 구성
            Message message = new Message(objectToJSON.getBytes(), mpb);

            // [STEP4] Direct Exchange를 이용하여 Routing Key와 함께 메시지 전달
            rabbitTemplate.convertAndSend("exchange.direct.priorityQueue", "classicPriorityQueue", message);
        } catch (JsonProcessingException jpe) {
            System.out.println("파싱 오류 발생");
        }


    }

    /**
     * 우선순위가 1인 메시지 전송
     *
     * @param messageDto
     */
    @Override
    public void sendPriority1Queue(MessageDto messageDto) {
        // [STEP1] MessageProperties 인스턴스 구성
        MessageProperties mpb = MessagePropertiesBuilder.newInstance()
                .setPriority(1)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // [STEP2] 메시지 객체 직렬화 수행
            String objectToJSON = objectMapper.writeValueAsString(messageDto);

            // [STEP3] 직렬화 객체와 메시지 정보로 메시지 객체 구성
            Message message = new Message(objectToJSON.getBytes(), mpb);

            // [STEP4] Direct Exchange를 이용하여 Routing Key와 함께 메시지 전달
            rabbitTemplate.convertAndSend("exchange.direct.priorityQueue", "classicPriorityQueue", message);
        } catch (JsonProcessingException jpe) {
            System.out.println("파싱 오류 발생");
        }
    }

    /**
     * 쿼럼 큐로 메시지를 전송합니다.
     *
     * @param messageDto
     */
    @Override
    @Transactional(readOnly = true)
    public void sendQuorumQueue(MessageDto messageDto) {

        // [STEP1] MessageProperties 인스턴스 구성
        MessageProperties mpb = MessagePropertiesBuilder.newInstance()
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // [STEP2] 메시지 객체 직렬화 수행
            String objectToJSON = objectMapper.writeValueAsString(messageDto);

            // [STEP3] 직렬화 객체와 메시지 정보로 메시지 객체 구성
            Message message = new Message(objectToJSON.getBytes(), mpb);

            // [STEP4] Direct Exchange를 이용하여 Routing Key와 함께 메시지 전달
            rabbitTemplate.convertAndSend("exchange.direct.quorumQueue", "quorumQueue", message);
        } catch (JsonProcessingException jpe) {
            System.out.println("파싱 오류 발생");
        }

    }

}
