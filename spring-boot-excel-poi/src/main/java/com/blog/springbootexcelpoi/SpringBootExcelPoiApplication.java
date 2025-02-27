package com.blog.springbootexcelpoi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootExcelPoiApplication {

    public static void main(String[] args) {

        // 애플리케이션 시작 전에 headless 모드 설정
        System.setProperty("java.awt.headless", "true");
        SpringApplication.run(SpringBootExcelPoiApplication.class, args);

    }

}
