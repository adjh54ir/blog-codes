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

    void directSendMessage(MessageDto messageDto);      // Direct Exchange 방식 이용

    void fanoutSendMessage(MessageDto messageDto);      // Fanout Exchange 방식 이용

    void headerSendMessage(MessageDto messageDto);      // Header Exchange 방식 이용

    void topicSendMessage(MessageDto messageDto);       // Topic Exchange 방식 이용
}
