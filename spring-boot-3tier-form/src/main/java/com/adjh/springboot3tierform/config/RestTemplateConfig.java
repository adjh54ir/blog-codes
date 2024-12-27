package com.adjh.springboot3tierform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * RestTemplate 구성합니다.
 *
 * @author : jonghoon
 * @fileName : RestTemplateConfig
 * @since : 11/1/24
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 사전 기본이 되는 RestTemplate 구성합니다.
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // RestTemplate 이용 중 클라이언트의 한글 깨짐 증상에 대한 수정
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }
}
