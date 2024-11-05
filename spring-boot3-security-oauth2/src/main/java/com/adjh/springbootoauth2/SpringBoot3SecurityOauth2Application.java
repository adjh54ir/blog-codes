package com.adjh.springbootoauth2;

import com.adjh.springbootoauth2.dto.properties.OAuth2ClientProperties;
import com.adjh.springbootoauth2.dto.properties.OAuth2ClientRecordProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({OAuth2ClientProperties.class, OAuth2ClientRecordProperties.class})
@SpringBootApplication
public class SpringBoot3SecurityOauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoot3SecurityOauth2Application.class, args);
    }

}
