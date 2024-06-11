package com.adjh.springbootrabbitmq.service.impl;

import com.adjh.springbootrabbitmq.dto.MessageDto;
import com.adjh.springbootrabbitmq.model.constant.ConstQueueAttr;
import com.adjh.springbootrabbitmq.service.ProducerQueueService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 다양한 큐를 테스트 해보기 위한 서비스 구현체입니다.
 *
 * @author : lee
 * @fileName : ProducerQueueServiceImpl
 * @since : 24. 6. 10.
 */
public class ProducerQueueServiceImpl implements ProducerQueueService {

    private final RabbitTemplate rabbitTemplate;

    public ProducerQueueServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendClassicQueue(MessageDto messageDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // [STEP1] DTO -> String 직렬화 수행
            String objectToJSON = objectMapper.writeValueAsString(messageDto);

            // [STEP2] 메시지 속성 지정
            MessageProperties mpb = MessagePropertiesBuilder.newInstance()
                    // 1. 메시지의 Expiration 지정(TTL : 1초)
                    .setHeader(ConstQueueAttr.QUEUE_TTL, 1000) // or .setExpiration() 이용
                    // 2. 메시지의 우선순위 지정
                    .setHeader(ConstQueueAttr.QUEUE_PRIORITY, 2) // or .setPriority() 이용
                    // 3. 데드 레터링 지정
                    .setHeader(ConstQueueAttr.QUEUE_DEAD_LETTER_EXCHANGE, "my-dlx")
                    // 4. 큐 길이 제한 지정
                    .setHeader(ConstQueueAttr.QUEUE_MAX_LENGTH, 1000)
                    // 5. 큐 내구성 설정 지정
                    .setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                    .build();
            // [STEP4] 메시지 객체로 구성
            Message message = new Message(objectToJSON.getBytes(), mpb);

            // [STEP5] 라우터를 기반으로 큐에 메시지 전송
            rabbitTemplate.convertAndSend("exchange.direct.processing", "processing", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
