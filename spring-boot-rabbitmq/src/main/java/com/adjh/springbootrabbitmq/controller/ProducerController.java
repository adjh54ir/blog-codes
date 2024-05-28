package com.adjh.springbootrabbitmq.controller;

import com.adjh.springbootrabbitmq.dto.MessageDto;
import com.adjh.springbootrabbitmq.service.ProducerService;
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
 * @author : jonghoon
 * @fileName : ProducerController
 * @since : 5/25/24
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/producer")
public class ProducerController {

    private final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    /**
     * 생산자(Proceduer)가 Direct Exchange 메시지를 전송합니다.
     *
     * @param messageDto
     * @return
     */
    @PostMapping("/directMessage")
    public ResponseEntity<?> directSendMessage(@RequestBody MessageDto messageDto) {
        String result = "";
        producerService.directSendMessage(messageDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 생산자(Proceduer)가 Fanout Exchange 메시지를 전송합니다.
     *
     * @param messageDto
     * @return
     */
    @PostMapping("/fanoutMessage")
    public ResponseEntity<?> fanoutSendMessage(@RequestBody MessageDto messageDto) {
        String result = "";
        producerService.fanoutSendMessage(messageDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 생산자(Proceduer)가 메시지를 전송합니다.
     *
     * @param messageDto
     * @return
     */
    @PostMapping("/headersMessage")
    public ResponseEntity<?> headerSendMessage(@RequestBody MessageDto messageDto) {
        String result = "";
        producerService.headerSendMessage(messageDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 생산자(Proceduer)가 메시지를 전송합니다.
     *
     * @param messageDto
     * @return
     */
    @PostMapping("/topicMessage")
    public ResponseEntity<?> topicSendMessage(@RequestBody MessageDto messageDto) {
        String result = "";
        producerService.topicSendMessage(messageDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
