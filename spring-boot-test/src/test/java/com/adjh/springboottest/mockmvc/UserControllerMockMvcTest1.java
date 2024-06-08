package com.adjh.springboottest.mockmvc;

import com.adjh.springboottest.controller.UserController;
import com.adjh.springboottest.dto.UserDto;
import com.adjh.springboottest.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserControllerMockMvcTest1
 * @since : 6/8/24
 */
@ExtendWith(MockitoExtension.class)
public class UserControllerMockMvcTest1 {


    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    /**
     * 사용자의 단건을 조회하는 API가 수행이 되는지 확인하는 MockMVC 테스트
     * - 해당 부분에서 서비스 수행에 대해서는 관여를 하지 않습니다.
     *
     * @throws Exception
     */
    @Test
    @DisplayName("코드 단건을 조회합니다.")
    void selectUserByUserSq() throws Exception {

        // given
        UserDto userDto = UserDto.builder().userSq(1).build();
        ObjectMapper om = new ObjectMapper();
        String result = om.writeValueAsString(userDto);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/user/userConn")
                        .content(result)
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        MvcResult mvcResult = resultActions
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        System.out.println("mvcResult :: " + mvcResult.getResponse().getContentAsString());
    }


    /**
     * 사용자의 다건을 조회하는 API가 수행이 되는지 확인하는 MockMVC 테스트
     * - 해당 부분에서 서비스 수행에 대해서는 관여를 하지 않습니다.
     *
     * @throws Exception
     */
    @Test
    @DisplayName("사용자 전체 리스트를 조회합니다..")
    void selectUserList() throws Exception {

        // given
        UserDto userDto = UserDto.builder().build();
        ObjectMapper om = new ObjectMapper();
        String result = om.writeValueAsString(userDto);

        // when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/user/usersConn")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(result));
        // then
        MvcResult mvcResult = resultActions
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        System.out.println("mvcResult :: " + mvcResult.getResponse().getContentAsString());
    }


}
