package com.adjh.springboot3security.controller;

import com.adjh.springboot3security.model.dto.UserDto;
import com.adjh.springboot3security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * [View] 로그인 페이지를 엽니다.
     *
     * @param model Model
     * @return ApiResponseWrapper<ApiResponse> : 응답 결과 및 응답 코드 반환
     */
    @PostMapping("/login")
    public String loginPage(Model model) {
        return "pages/login/loginPage";
    }

    /**
     * [API] 사용자 리스트 조회
     *
     * @param userDto UserDto
     * @return ResponseEntity
     */
    @PostMapping("/user")
    public ResponseEntity<Object> selectCodeList(@RequestBody UserDto userDto) {
        List<UserDto> selectUserList = userService.selectUserList(userDto);
        return new ResponseEntity<>(selectUserList, HttpStatus.OK);
    }
}
