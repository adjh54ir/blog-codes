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
    @DisplayName("코드리스트 테스트 ")
    void codeList() {
        System.out.println("[+] 코드리스트를 조회합니다.");

        // Given
        UserDto userDto = UserDto
                .builder()
                .userSq(1)
                .build();
        // When
        List<UserDto> result = userService.selectUserList(userDto);

        // Then
        Assertions.assertNotNull(result);
    }
}
