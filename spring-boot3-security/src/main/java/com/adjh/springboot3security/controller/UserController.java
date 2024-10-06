package com.adjh.springboot3security.controller;

import com.adjh.springboot3security.model.dto.UserDto;
import com.adjh.springboot3security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 로그인 테스트를 위해 임시로 구성한 Controller
 *
 * @author : jonghoon
 * @fileName : WebConfig
 * @since : 10/1/24
 */
@Slf4j
@Controller
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * [API] 사용자 리스트 조회
     *
     * @param userDto UserDto
     * @return ResponseEntity
     */
    @PostMapping("/user")
    public ResponseEntity<Object> selectUser(@RequestBody UserDto userDto) {
        List<UserDto> selectUserList = userService.selectUserList(userDto);
        return new ResponseEntity<>(selectUserList, HttpStatus.OK);
    }
}
