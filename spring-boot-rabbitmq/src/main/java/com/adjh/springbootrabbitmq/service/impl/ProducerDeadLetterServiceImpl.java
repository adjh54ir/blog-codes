package com.adjh.springbootrabbitmq.service.impl;

import com.adjh.springbootrabbitmq.dto.MessageDto;
import com.adjh.springbootrabbitmq.service.ProducerDeadLetterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : ProducerDeadLetterServiceImpl
 * @since : 24. 6. 5.
 */
@Service
@RequiredArgsConstructor
public class ProducerDeadLetterServiceImpl implements ProducerDeadLetterService {

    private final RabbitTemplate rabbitTemplate;

    /**
     * Direct 방식을 이용하여 메시지 전송
     *
     * @param messageDto
     */
    @Override
    @Transactional(readOnly = true)
    public void directToDeadLetterSendMessage(MessageDto messageDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // [STEP1] DTO -> String 직렬화 수행
            String objectToJSON = objectMapper.writeValueAsString(messageDto);

            // [STEP2] 메시지 속성 지정
            MessageProperties messageProperties = new MessageProperties();

            // 1. 메시지의 Expiration 지정(TTL : 1초)
            messageProperties.setExpiration("1000");

            // 2. 메시지의 우선순위 지정
            messageProperties.setPriority(2);

            // 3. 데드 레터링 지정
            messageProperties.setHeader("x-dead-letter-exchange", "my-dlx");

            // 4. 큐 길이 제한 지정
            messageProperties.setHeader("x-max-length", 1000);

            // 5. 내구성 설정 지정
            messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);

            // [STEP4] 메시지 객체로 구성
            Message message = new Message(objectToJSON.getBytes(), messageProperties);

            // [STEP5] 라우터를 기반으로 큐에 메시지 전송
            rabbitTemplate.convertAndSend("exchange.direct.processing", "processing", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
