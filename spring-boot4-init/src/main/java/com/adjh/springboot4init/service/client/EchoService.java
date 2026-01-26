package com.adjh.springboot4init.service.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Map;

/**
 * HTTP Service Interface Clients 사용예시
 *
 * @author : leejonghoon
 * @fileName : EchoService
 * @since : 26. 1. 15.
 */
@Service
@HttpExchange(
        url = "https://echo.zuplo.io",
        contentType = MediaType.APPLICATION_JSON_VALUE)
public interface EchoService {

    @PostExchange
    Map<?, ?> echo(@RequestBody Map<String, String> message);
}
