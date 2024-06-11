package com.adjh.springbootrabbitmq.service;

import com.adjh.springbootrabbitmq.dto.MessageDto;

/**
 * 생성자가 다양한 큐를 기반으로 확인합니다.
 *
 * @author : lee
 * @fileName : ProducerQueueService
 * @since : 24. 6. 10.
 */
public interface ProducerQueueService {

    void sendClassicQueue(MessageDto messageDto);
}
