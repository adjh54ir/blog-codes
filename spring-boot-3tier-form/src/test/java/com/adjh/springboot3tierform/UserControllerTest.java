package com.adjh.springboot3tierform;

import com.adjh.springboot3tierform.config.RestTemplateConfig;
import com.adjh.springboot3tierform.model.dto.UserDto;
import com.adjh.springboot3tierform.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : UserControllerTest
 * @since : 2024. 12. 27.
 */
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @Test
    void testMultipleUserList() {
        // 테스트용 UserDto 생성
        UserDto testUserDto = UserDto.builder().build();
        // 필요한 테스트 데이터 설정
        MultiValueMap<String, Object> requestBodyMap = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> requestMap = new HttpEntity<>(requestBodyMap);

        // 10번의 API 호출 테스트
        for (int i = 0; i < 10; i++) {
            List<UserDto> response = userService.selectUserList(testUserDto);
            // 응답 검증
            assertNotNull(response);
            List<UserDto> resultList = response;
            assertNotNull(resultList);

            System.out.println("Test #" + (i + 1) + " Response received");
            System.out.println("Response size: " + resultList.size());
        }
    }
}
