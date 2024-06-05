package com.adjh.springbootrabbitmq.controller;

import com.adjh.springbootrabbitmq.dto.MessageDto;
import com.adjh.springbootrabbitmq.service.ProducerDeadLetterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : ProducerDeadLetterController
 * @since : 24. 6. 5.
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/producer/deadLetter")
public class ProducerDeadLetterController {

    private final ProducerDeadLetterService producerDeadLetterService;

    public ProducerDeadLetterController(ProducerDeadLetterService producerDeadLetterService) {
        this.producerDeadLetterService = producerDeadLetterService;
    }

    /**
     * 생산자(Proceduer)가 Direct Exchange 메시지를 전송합니다. 단, DeadLetter 방식으로 오류를 발생시킵니다.
     *
     * @param messageDto
     * @return
     */
    @PostMapping("/deadLetter")
    public ResponseEntity<?> deadLetterMessage(@RequestBody MessageDto messageDto) {
        System.out.println("전달 받은 메시지 :: " + messageDto.toString());
        String result = "";
        producerDeadLetterService.directToDeadLetterSendMessage(messageDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
