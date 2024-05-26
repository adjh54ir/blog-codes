package com.adjh.springbootrabbitmq.service;

import com.adjh.springbootrabbitmq.dto.MessageDto;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : ProducerService
 * @since : 5/25/24
 */
public interface ProducerService {

    // 메시지를 큐로 전송 합니다.
    void sendMessage(MessageDto messageDto);
}
