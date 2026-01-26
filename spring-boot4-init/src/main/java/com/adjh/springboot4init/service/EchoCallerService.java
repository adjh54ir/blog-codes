package com.adjh.springboot4init.service;

import com.adjh.springboot4init.service.client.EchoService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * HTTP Service Interface Clients 외부 통신 이후 전처리 및 로깅을 처리하는 호출 Wrapper
 *
 * @author : leejonghoon
 * @fileName : EchoCallerService
 * @since : 26. 1. 20.
 */
@Service
public class EchoCallerService {
    private final EchoService echoService;

    public EchoCallerService(EchoService echoService) {
        this.echoService = echoService;
    }

    public Map<?, ?> callEcho(Map<String, String> message) {
        return echoService.echo(message);
    }
}
