package com.adjh.springboottest.service;

import com.adjh.springboottest.dao.UserDao;
import com.adjh.springboottest.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : ServiceTest
 * @since : 5/31/24
 */
@SpringBootTest
class ServiceTests {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("DB 사용자 리스트 테스트")
    void selectUserList() {
        // Given
        UserDto userDto = UserDto
                .builder()
                .build();
        // When
        List<UserDto> result = userService.selectUserList(userDto);

        System.out.println("[+] DB 사용자 리스트를 조회합니다. " + result);

        // Then
        Assertions.assertNotNull(result);
    }
}
