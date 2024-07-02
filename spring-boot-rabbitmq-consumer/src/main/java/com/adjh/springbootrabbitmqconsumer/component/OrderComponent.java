package com.adjh.springbootrabbitmqconsumer.component;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Order Service에 대한 이벤트 소비자를 구성합니다.
 *
 * @author : lee
 * @fileName : OrderComponent
 * @since : 24. 5. 28.
 */
@Component
public class OrderComponent {

    @RabbitListener(queues = "queue1")
    public void receiveMessage(String msg) {
        System.out.println("Queue1 내의 결과 값을 반환 받습니다 " + msg);
    }

    @RabbitListener(queues = "queue2")
    public void receiveMessage2(String msg) {
        System.out.println("Queue2 내의 결과 값을 반환 받습니다 " + msg);
    }

    @RabbitListener(queues = "queue3")
    public void receiveMessage3(String msg) {
        System.out.println("Queue3 내의 결과 값을 반환 받습니다 " + msg);
    }

    @RabbitListener(queues = "queue4")
    public void receiveMessage4(String msg) {
        System.out.println("Queue4 내의 결과 값을 반환 받습니다 " + msg);
    }

    @RabbitListener(queues = "queue5")
    public void receiveMessage5(String msg) {
        System.out.println("Queue5 내의 결과 값을 반환 받습니다 " + msg);
    }

    @RabbitListener(queues = "classicPriorityQueue")
    public void receiveClassicPriorityQueue(String msg) {
        System.out.println("classicPriorityQueue 내의 결과값을 받습니다 : " + msg);
    }
    @RabbitListener(queues = "quorumQueue")
    public void quorumQueue(String msg) {
        System.out.println("quorumQueue 내의 결과값을 받습니다 : " + msg);
    }
}