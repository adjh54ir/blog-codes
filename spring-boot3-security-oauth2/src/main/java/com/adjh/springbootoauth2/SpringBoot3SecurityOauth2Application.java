package com.adjh.springbootoauth2;

import com.adjh.springbootoauth2.config.properties.OAuth2ClientProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;


@SpringBootApplication
// 패키지 경로에 따라 여러 클래스 조회 및 등록
@ConfigurationPropertiesScan("com.adjh.springbootoauth2.config.properties")
public class SpringBoot3SecurityOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3SecurityOauth2Application.class, args);
    }

}
