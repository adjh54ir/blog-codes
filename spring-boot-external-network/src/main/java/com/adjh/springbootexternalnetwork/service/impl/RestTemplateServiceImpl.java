package com.adjh.springbootexternalnetwork.service.impl;

import com.adjh.springbootexternalnetwork.config.RestTemplateConfig;
import com.adjh.springbootexternalnetwork.service.RestTemplateService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : RestTemplateServiceImpl
 * @since : 11/23/24
 */
@Service
public class RestTemplateServiceImpl implements RestTemplateService {

    final RestTemplateConfig restTemplateConfig;

    private final String TEST_URL = "https://jsonplaceholder.typicode.com";

    public RestTemplateServiceImpl(RestTemplateConfig restTemplateConfig) {
        this.restTemplateConfig = restTemplateConfig;
    }

    /**
     * 기본적으로 사용하는 Header를 구성하여 반환합니다.
     *
     * @return
     */
    private HttpHeaders defaultHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        return headers;
    }


    /**
     * RESTTemplate을 이용한 데이터 통신
     *
     * @param object
     * @return
     */
    @Override
    public Object externalCmnc(Object object) {

        Object resultObj = null;
        // 요청 Body 데이터 구성
        MultiValueMap<String, Object> requestBodyMap = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> requestMap = new HttpEntity<>(requestBodyMap, this.defaultHeader());
        try {
            resultObj = restTemplateConfig
                    .restTemplate()
                    .exchange(TEST_URL + "/posts", HttpMethod.GET, requestMap, new ParameterizedTypeReference<>() {
                    });
            System.out.println("resultObj :: " + resultObj);

        } catch (Exception e) {
            System.out.println("[-] 토큰 요청 중에 오류가 발생하였습니다. {}" + e.getMessage());
        }
        return resultObj;
    }
}
