package com.adjh.springbootrabbitmq.controller;

import com.adjh.springbootrabbitmq.dto.MessageDto;
import com.adjh.springbootrabbitmq.service.ProducerExchangeService;
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
@RequestMapping(value = "/api/v1/producer/exchange")
public class ProducerExchangeController {

    private final ProducerExchangeService producerExchangeService;

    public ProducerExchangeController(ProducerExchangeService producerExchangeService) {
        this.producerExchangeService = producerExchangeService;
    }

    /**
     * 생산자(Proceduer)가 Direct Exchange 메시지를 전송합니다.
     *
     * @param messageDto
     * @return
     */
    @PostMapping("/direct")
    public ResponseEntity<?> directSendMessage(@RequestBody MessageDto messageDto) {
        String result = "";
        producerExchangeService.directSendMessage(messageDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 생산자(Proceduer)가 Fanout Exchange 메시지를 전송합니다.
     *
     * @param messageDto
     * @return
     */
    @PostMapping("/fanout")
    public ResponseEntity<?> fanoutSendMessage(@RequestBody MessageDto messageDto) {
        String result = "";
        producerExchangeService.fanoutSendMessage(messageDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 생산자(Proceduer)가 Header Exchange 메시지를 전송합니다.
     *
     * @param messageDto
     * @return
     */
    @PostMapping("/headers")
    public ResponseEntity<?> headerSendMessage(@RequestBody MessageDto messageDto) {
        String result = "";
        producerExchangeService.headerSendMessage(messageDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 생산자(Proceduer)가 Topic Exchange 메시지를 전송합니다.
     *
     * @param messageDto
     * @return
     */
    @PostMapping("/topic")
    public ResponseEntity<?> topicSendMessage(@RequestBody MessageDto messageDto) {
        String result = "";
        producerExchangeService.topicSendMessage(messageDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
