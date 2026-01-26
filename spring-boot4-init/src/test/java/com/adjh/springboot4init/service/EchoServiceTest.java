package com.adjh.springboot4init.service;

import com.adjh.springboot4init.service.client.EchoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class EchoServiceTest {

    @Autowired
    private EchoService echoService;

    @Test
    void echo_api_call_test() {
        // 1️⃣ 요청 바디
        Map<String, String> request = Map.of(
                "msg", "hello",
                "type", "test"
        );

        // 2️⃣ 실제 HTTP POST 요청 발생
        Map<?, ?> response = echoService.echo(request);

        // 3️⃣ 결과 출력
        System.out.println("=== RESPONSE ===");
        response.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}
