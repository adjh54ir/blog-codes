package com.adjh.springboot4init.controller;

import com.adjh.springboot4init.service.EchoCallerService;
import com.adjh.springboot4init.service.client.EchoService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 클라이언트의 요청 값을 통해 외부 API로 요청 값을 전달하고 결과를 응답하는 Controller
 *
 * @author : leejonghoon
 * @fileName : EchoController
 * @since : 26. 1. 15.
 */
@RestController
@RequestMapping(value = "/api/echo")
public class EchoController {

    private final EchoService echoService;

    public EchoController(EchoService echoService) {
        this.echoService = echoService;
    }

    @PostMapping(value = "/test")
    public Map<?, ?> testEcho(@RequestBody Map<String, String> request) {
        System.out.println("버전 1 API가 호출이 되었습니다.");
        return echoService.echo(request);
    }


//    @PostMapping(value = "/test")
//    public Map<?, ?> testEcho2(@RequestBody Map<String, String> request) {
//        System.out.println("버전 2 API가 호출이 되었습니다.");
//        return echoService.echo(request);
//    }
}
