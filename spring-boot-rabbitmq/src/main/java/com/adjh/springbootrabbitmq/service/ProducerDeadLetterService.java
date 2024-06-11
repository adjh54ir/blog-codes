package com.adjh.springbootrabbitmq.service;

import com.adjh.springbootrabbitmq.dto.MessageDto;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : ProducerDeadLetterService
 * @since : 24. 6. 5.
 */
@Service
public interface ProducerDeadLetterService {
    void directToDeadLetterSendMessage(MessageDto messageDto);  // Direct Exchange 방식을 이용하며 Dead Letter 테스트 하기 위해 이용
}
