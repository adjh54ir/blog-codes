package com.adjh.springboottest.mokito;

import com.adjh.springboottest.dao.UserDao;
import com.adjh.springboottest.dto.UserDto;
import com.adjh.springboottest.service.impl.UserServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : SimpleMockitoTests
 * @since : 6/16/24
 */
@ExtendWith(MockitoExtension.class)
public class SimpleMockitoTests {


    @Mock
    private UserDao userDao;

    @Mock
    private SqlSession sqlSession;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    @DisplayName("기본 요소를 테스트 해봅니다.")
    void testSimple() {
        // 1. 모의 객체 생성 : Mock
        List<String> mockList = Mockito.mock(List.class);

        // 2. 메서드 호출 예상 동작 설정 : Stub
        Mockito.when(mockList.size()).thenReturn(5);
        System.out.println("동작 값을 확인 합니다 :: " + mockList.size());   // 동작 값을 확인 합니다 :: 5

        // 3. 메서드 호출 검증 : Verify
        Mockito.verify(mockList).size();
    }

    @Test
    @DisplayName("기본 요소를 테스트 해봅니다.-2")
    void testSimple2() {

        // 1. Mocking a class
        List<String> mockList = Mockito.mock(List.class);

        // 2. Setting up method call and return value
        Mockito.doReturn(5).when(mockList).size();
        System.out.println("Checking the behavior value :: " + mockList.size());

        // 3. Verifying method call
        Mockito.verify(mockList).size();

        // 4. Verifying no more interactions
        Mockito.verifyNoMoreInteractions(mockList);
    }

    @Test
    @DisplayName("기본 요소를 테스트 해봅니다.-3")
    void testSimple3() {

        // *************** given : 사전 조건 ******************
        List<UserDto> mockDto = Mockito.mock(List.class);

        UserDto userDto = UserDto.builder().userSq(1).build();
        // *************** when : 동작 검증 ******************
        Mockito.when(userDao.selectUserList(userDto)).thenReturn(mockDto);
        Mockito.lenient().when(sqlSession.getMapper(UserDto.class)).thenReturn(userDto);
        List<UserDto> result = userService.selectUserList(userDto);

        // *************** when : 결과 검증 ******************
        Assertions.assertEquals(mockDto, result);

    }

}
