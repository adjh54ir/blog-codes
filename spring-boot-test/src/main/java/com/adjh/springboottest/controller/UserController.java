package com.adjh.springboottest.controller;

import com.adjh.springboottest.dto.UserDto;
import com.adjh.springboottest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : UserController
 * @since : 2024. 5. 21.
 */
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * API 통신이 잘되고 있는지에 대해서만 테스트하기 위한 API
     * - Service 관여 X
     *
     * @param userDto
     * @return
     */
    @PostMapping("/userConn")
    public ResponseEntity<UserDto> userConn(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    /**
     * API 통신이 잘되고 있는지에 대해서만 테스트하기 위한 API
     * - Service 관여 X
     *
     * @param userDto
     * @return
     */
    @PostMapping("/usersConn")
    public ResponseEntity<UserDto> usersConn(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }


    @PostMapping("/user")
    public ResponseEntity<UserDto> selectUserByUserSq(@RequestBody UserDto userDto) {
        UserDto result = userService.selectUserByUserSq(userDto);

        System.out.println("서비스 결과값 확인 :: " + result);

        HttpStatus resultStatus = HttpStatus.OK;

//        // 값이 존재하지 않으면 404 오류로 강제 테스트 오류 발생
        if (result == null) {
            resultStatus = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(result, resultStatus);
    }

    @PostMapping("/users")
    public ResponseEntity<List<UserDto>> selectUserList(@RequestBody UserDto userDto) {
        List<UserDto> result = userService.selectUserList(userDto);
        System.out.println("파라미터 값 :: " + userDto.toString());

        HttpStatus resultStatus = HttpStatus.OK;

        // 값이 존재하지 않으면 404 오류로 강제 테스트 오류 발생
        if (result.size() == 0) {
            resultStatus = HttpStatus.NOT_FOUND;
        }

        return new ResponseEntity<>(result, resultStatus);
    }


}
