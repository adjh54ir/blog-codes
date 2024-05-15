package com.adjh.springboot3tierform.controller;

import com.adjh.springboot3tierform.model.dto.UserDto;
import com.adjh.springboot3tierform.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserController
 * @since : 5/15/24
 */
@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 사용자 리스트 조회
     *
     * @param userDto
     * @return
     */
    @PostMapping("/users")
    public ResponseEntity<List<UserDto>> selectUserList(@RequestBody UserDto userDto) {
        List<UserDto> resultList = userService.selectUserList(userDto);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    /**
     * 사용자 등록
     *
     * @param userDto
     * @return
     */
    @PostMapping("/user")
    public ResponseEntity<Integer> insertUser(@RequestBody UserDto userDto) {
        int result = userService.insertUser(userDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 사용자 등록
     *
     * @param userDto
     * @return
     */
    @PutMapping("/user")
    public ResponseEntity<Integer> updateUser(@RequestBody UserDto userDto) {
        int result = userService.updateUser(userDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    /**
     * 사용자 삭제
     *
     * @param userDto
     * @return
     */
    @DeleteMapping("/user")
    public ResponseEntity<Integer> deleteUser(@RequestBody UserDto userDto) {
        int result = userService.deleteUser(userDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
