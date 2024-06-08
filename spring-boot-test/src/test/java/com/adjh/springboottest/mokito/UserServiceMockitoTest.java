package com.adjh.springboottest.mokito;

import com.adjh.springboottest.dao.UserDao;
import com.adjh.springboottest.dto.UserDto;
import com.adjh.springboottest.service.UserService;
import com.adjh.springboottest.service.impl.UserServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.lenient;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserServiceMockitoTest
 * @since : 6/8/24
 */
@ExtendWith(MockitoExtension.class)
class UserServiceMockitoTest {

    @Mock
    private SqlSession sqlSession;

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("사용자 단건 조회 테스트 합니다.")
    void testSelectUserItemByUserSq() {

        // **************************************************
        // *************** given : 사전 조건 ******************
        // **************************************************
        // 1. 가상의 객체를 구성합니다.
        UserDto mockUserDto = UserDto.builder().build();

        UserDto userDto = userService.selectUserByUserSq(mockUserDto);
        System.out.println("결과값 :: " + userDto);


        // **************************************************
        // *************** when : 동작 검증 ******************
        // **************************************************
        // 2. 가상의 userDao 메서드를 호출하여 가상의 객체로 반환하도록 구성
        Mockito.when(userDto).thenReturn(mockUserDto);

        System.out.println("1. 가상의 객체를 호출 한 경우 :: " + mockUserDto);

        // 3. 가상의 sqlSession에 Mapper를 지정해줍니다. (* 해당 부분은 서비스 구현체(ServiceImpl)에서 사용되는 데이터를 구성하였습니다)
        Mockito.lenient().when(sqlSession.getMapper(UserDao.class)).thenReturn(userDao);
        // 4. 테스트 대상 메서드를 호출합니다.
        UserDto result = userService.selectUserByUserSq(mockUserDto);


        // **************************************************
        // *************** when : 결과 검증 ******************
        // **************************************************
        // 5. 가상의 Mapper 값과 테스트 대상 메서드를 비교합니다.
        Assertions.assertEquals(mockUserDto, result);

        System.out.println("1. 직접 서비스를 호출 한 경우 :: " + result);
        System.out.println("2. 가상의 객체를 호출 한 경우 :: " + mockUserDto);


    }

    @Test
    @DisplayName("객체 값을 기반으로 여러개의 코드 값들을 조회합니다.")
    void testUserList() {

        // *************** given : 사전 조건 ******************
        // 1. 가상의 객체를 구성합니다.
        UserDto mockUserDto = UserDto.builder().build();

        // 2. Service와 비교할 반환값을 구성합니다.
        List<UserDto> expectedUserList = new ArrayList<>();

        // *************** when : 동작 검증 ******************
        // 3. 가상의 codeMapper의 메서드를 호출하여 가상의 객체로 반환되도록 지정합니다.
        Mockito.when(userDao.selectUserList(mockUserDto)).thenReturn(expectedUserList);

        // 4. 가상의 sqlSession에 Mapper를 지정해줍니다. (* 해당 부분은 서비스 구현체(ServiceImpl)에서 사용되는 데이터를 구성하였습니다)
        Mockito.lenient().when(sqlSession.getMapper(UserDao.class)).thenReturn(userDao);

        // 5. 테스트 대상 메서드를 호출합니다.
        List<UserDto> actualUserList = userService.selectUserList(mockUserDto);

        System.out.println("1. 가상의 객체를 호출한 경우 :: " + expectedUserList);
        System.out.println("2. 직접 서비스를 호출한 경우 :: " + actualUserList);

        // *************** when : 결과 검증 ******************
        // 6. 반환된 코드 리스트가 예상한 값과 일치하는지 검증합니다.
        Assertions.assertEquals(expectedUserList, actualUserList);
    }


}
