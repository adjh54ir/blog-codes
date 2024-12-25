package com.adjh.springbootexternalnetwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients // FeignClient 사용을 선언합니다.
@SpringBootApplication
public class SpringBootExternalNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootExternalNetworkApplication.class, args);
    }

}
