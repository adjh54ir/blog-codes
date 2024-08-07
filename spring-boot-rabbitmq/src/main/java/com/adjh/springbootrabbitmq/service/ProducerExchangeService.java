package com.adjh.springbootrabbitmq.service;

import com.adjh.springbootrabbitmq.dto.MessageDto;
import org.springframework.stereotype.Service;

/**
 * 메시지 생성자의 Exchange 별 서비스 처리
 *
 * @author : jonghoon
 * @fileName : ProducerExchangeService
 * @since : 5/25/24
 */
@Service
public interface ProducerExchangeService {

    void directSendMessage(MessageDto messageDto);      // Direct Exchange 방식 이용

    void fanoutSendMessage(MessageDto messageDto);      // Fanout Exchange 방식 이용

    void headerSendMessage(MessageDto messageDto);      // Header Exchange 방식 이용

    void topicSendMessage(MessageDto messageDto);       // Topic Exchange 방식 이용
}
