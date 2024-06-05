package com.adjh.springbootrabbitmqconsumer.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 이벤트 소비자를 구성합니다.
 *
 * @author : lee
 * @fileName : DeadLetterComponent
 * @since : 24. 6. 5.
 */
@Component
public class DeadLetterComponent {

    @RabbitListener(queues = "processingQueue")
    public void processingQueueMessage(String msg) {
        System.out.println("Processing Queue 내의 결과 값을 반환 받습니다 " + msg);
    }

    @RabbitListener(queues = "deadLetterQueue")
    public void receiveErrorMessage(String msg) {
        System.out.println("오류가 발생하였을때 저장되는 메시지 : " + msg);
    }
}
