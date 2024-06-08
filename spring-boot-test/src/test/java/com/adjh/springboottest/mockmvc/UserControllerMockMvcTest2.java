package com.adjh.springboottest.mockmvc;

import com.adjh.springboottest.dto.UserDto;
import com.adjh.springboottest.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserControllerMockMvcTest2
 * @since : 6/8/24
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerMockMvcTest2 {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("코드 단건을 조회합니다.")
    void selectUserByUserSq() throws Exception {

        // given
        UserDto userDto = UserDto.builder().userSq(1).build();

        // when
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer xxxxxxx");

        // then
        HttpEntity entity = new HttpEntity(userDto, headers);
        ResponseEntity<UserDto> selectUserByUserSq = restTemplate.exchange("/api/v1/user/user", HttpMethod.POST, entity, UserDto.class);
        System.out.println("API 통신 결과값 :: " + selectUserByUserSq);

        Assertions.assertEquals(selectUserByUserSq.getStatusCode(), HttpStatus.OK);
        System.out.println("최종 통신 결과 :: " + selectUserByUserSq.getStatusCode());
    }

    @Test
    @DisplayName("사용자 전체 리스트를 조회합니다.")
    void selectCodeListTest() throws Exception {

        // given
        UserDto userDto = UserDto.builder().build();

        // when
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer xxxxxx");

        // then
        HttpEntity<UserDto> entity = new HttpEntity<>(userDto, headers);
        ResponseEntity<UserDto> selectUserList = restTemplate.exchange("/api/v1/user/user", HttpMethod.POST, entity, UserDto.class);
        System.out.println("API 통신 결과값 :: " + selectUserList);

        Assertions.assertEquals(selectUserList.getStatusCode(), HttpStatus.OK);
        System.out.println("최종 통신 결과 :: " + selectUserList.getStatusCode());
    }
}
