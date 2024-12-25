package com.adjh.springbootfcm.service.impl;

import com.adjh.springbootfcm.dto.FcmMessageDto;
import com.adjh.springbootfcm.dto.FcmSendDto;
import com.adjh.springbootfcm.service.FcmService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * FCM 서비스를 처리하는 구현체
 *
 * @author : FcmServiceImpl
 * @fileName : PushMessageServiceImpl
 * @since : 2/21/24
 */
@Slf4j
@Service
public class FcmServiceImpl implements FcmService {

    /**
     * 푸시 메시지 처리를 수행하는 비즈니스 로직
     *
     * @param fcmSendDto 모바일에서 전달받은 Object
     * @return 성공(1), 실패(0)
     */
    @Override
    public int sendMessageTo(FcmSendDto fcmSendDto) throws IOException {
        System.out.println("fcmSendDto :: " + fcmSendDto.toString());
        String message = makeMessage(fcmSendDto);
        RestTemplate restTemplate = new RestTemplate();

        /**
         * 추가된 사항 : RestTemplate 이용중 클라이언트의 한글 깨짐 증상에 대한 수정
         * @refernece : https://stackoverflow.com/questions/29392422/how-can-i-tell-resttemplate-to-post-with-utf-8-encoding
         */
        restTemplate.getMessageConverters()
                .add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(message, headers);

        // 해당 부분에서 'adjh54-a0189' 부분은 firebase 프로젝트명이 들어가는 내용입니다.
        String API_URL = "https://fcm.googleapis.com/v1/projects/adjh54-a0189/messages:send";
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);
            System.out.println(response.getStatusCode());
            return response.getStatusCode() == HttpStatus.OK ? 1 : 0;
        } catch (Exception e) {
            log.error("[-] FCM 전송 오류 :: " + e.getMessage());
            log.error("[-] 오류 발생 토큰 :: [" + fcmSendDto.getToken() + "]");
            log.error("[-] 오류 발생 메시지 :: [" + fcmSendDto.getBody() + "]");
            return 0;
        }
    }

    /**
     * Firebase Admin SDK의 비공개 키를 참조하여 Bearer 토큰을 발급 받습니다.
     *
     * @return Bearer token
     */
    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "firebase/adjh54-dev-firebase-key.json";

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

    /**
     * FCM 전송 정보를 기반으로 메시지를 구성합니다. (Object -> String)
     *
     * @param fcmSendDto FcmSendDto
     * @return String
     */
    private String makeMessage(FcmSendDto fcmSendDto) throws JsonProcessingException {

        ObjectMapper om = new ObjectMapper();
        FcmMessageDto fcmMessageDto = FcmMessageDto
                .builder()
                .message(FcmMessageDto.Message.builder()
                        .token(fcmSendDto.getToken())
                        .notification(FcmMessageDto.Notification.builder()
                                .title(fcmSendDto.getTitle())
                                .body(fcmSendDto.getBody())
                                .image(null)
                                .build()
                        ).build())
                .validateOnly(false)
                .build();

        return om.writeValueAsString(fcmMessageDto);
    }
}
