package com.adjh.springbootrabbitmq.controller;

import com.adjh.springbootrabbitmq.dto.MessageDto;
import com.adjh.springbootrabbitmq.service.ProducerQueueService;
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
 * @fileName : ProducerQueueController
 * @since : 24. 6. 11.
 */
@RestController
@RequestMapping(value = "/api/v1/producer/queue")
public class ProducerQueueController {

    private final ProducerQueueService producerQueueService;

    public ProducerQueueController(ProducerQueueService producerQueueService) {
        this.producerQueueService = producerQueueService;
    }

    /**
     * 생산자(Proceduer)가 Topic Exchange 메시지를 전송합니다.
     *
     * @param messageDto
     * @return
     */
    @PostMapping("/classicQueue")
    public ResponseEntity<?> topicSendMessage(@RequestBody MessageDto messageDto) {
        String result = "";
        producerQueueService.sendClassicQueue(messageDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
