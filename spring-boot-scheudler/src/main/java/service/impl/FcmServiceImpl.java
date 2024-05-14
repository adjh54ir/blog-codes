package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import mapper.FcmMapper;
import model.FcmMessageDto;
import model.FcmSendDeviceDto;
import model.FcmSendDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import service.FcmService;

import java.io.IOException;
import java.util.List;

/**
 * FCM 서비스를 처리하는 구현체
 *
 * @author : FcmServiceImpl
 * @fileName : PushMessageServiceImpl
 * @since : 2/21/24
 */
@Service
public class FcmServiceImpl implements FcmService {

    private final SqlSession sqlSession;

    public FcmServiceImpl(SqlSession ss) {
        this.sqlSession = ss;
    }


    /**
     * 푸시 메시지 처리를 수행하는 비즈니스 로직
     *
     * @param fcmSendDto 모바일에서 전달받은 Object
     * @return 성공(1), 실패(0)
     */
    @Override
    public void sendMessageTo(FcmSendDto fcmSendDto) throws IOException {

        String message = makeMessage(fcmSendDto);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + getAccessToken());

        HttpEntity<String> entity = new HttpEntity<>(message, headers);

        String API_URL = "https://fcm.googleapis.com/v1/projects/adjh-dev-test/messages:send";
        restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);
    }

    /**
     * FCM 전송 디바이스 리스트 조회
     */
    @Override
    @Transactional(readOnly = true)
    public List<FcmSendDeviceDto> selectFcmSendList() {
        FcmMapper udm = sqlSession.getMapper(FcmMapper.class);
        return udm.selectFcmSendList();
    }


    /**
     * Firebase Admin SDK의 비공개 키를 참조하여 Bearer 토큰을 발급 받습니다.
     *
     * @return Bearer token
     */
    private String getAccessToken() throws IOException {
        String firebaseConfigPath = "firebase/adjh54-dev-firebase-test-key.json";

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
        FcmMessageDto fcmMessageDto = FcmMessageDto.builder()
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
