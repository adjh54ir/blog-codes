package com.adjh.springbootrabbitmq.service;

import com.adjh.springbootrabbitmq.dto.MessageDto;
import org.springframework.stereotype.Service;

/**
 * 생성자가 다양한 큐를 기반으로 확인합니다.
 *
 * @author : lee
 * @fileName : ProducerQueueService
 * @since : 24. 6. 10.
 */
@Service
public interface ProducerQueueService {

    void sendClassicQueue(MessageDto messageDto);

    void sendQuorumQueue(MessageDto messageDto);


    // 우선순위가 가장 높은 큐
    void sendPriority5Queue(MessageDto messageDto);

    // 우선순위가 가장 낮은 큐
    void sendPriority1Queue(MessageDto messageDto);
}
