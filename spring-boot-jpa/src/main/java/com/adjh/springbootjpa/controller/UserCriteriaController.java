package com.adjh.springbootjpa.controller;

import com.adjh.springbootjpa.dto.UserDto;
import com.adjh.springbootjpa.entity.UserEntity;
import com.adjh.springbootjpa.repository.UserCriteriaRepository;
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
 * @fileName : UserCriteriaController
 * @since : 2024. 5. 10.
 */
@RequestMapping("/api/v1/userCriteria")
@RestController
public class UserCriteriaController {

    private final UserCriteriaRepository userService;

    public UserCriteriaController(UserCriteriaRepository userService) {
        this.userService = userService;
    }


    /**
     * [Criteria 쿼리방식-1] 사용자 조회
     *
     * @return
     */
    @PostMapping("/user")
    public ResponseEntity<Object> selectUser(@RequestBody UserDto userDto) {
        List<UserEntity> userEntityList = userService.selectUser();
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }

    /**
     * [Criteria 쿼리방식-2] 사용자 리스트 조회
     *
     * @return
     */
    @PostMapping("/users")
    public ResponseEntity<Object> selectUserList(@RequestBody UserDto userDto) {
        List<UserEntity> userEntityList = userService.selectUserList(userDto);
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }


    /**
     * [Criteria 쿼리방식-3] 동적 사용자 정렬
     *
     * @return
     */
    @PostMapping("/orderUser")
    public ResponseEntity<Object> testDynamicOrderQuery(@RequestBody UserDto userDto) {
        List<UserEntity> userEntityList = userService.testDynamicOrderQuery(userDto);
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }

    /**
     * [Criteria 쿼리방식-4] 동적 where 절 구성
     *
     * @return
     */
    @PostMapping("/userInfo")
    public ResponseEntity<Object> selectUserInfo(@RequestBody UserDto userDto) {
        List<UserEntity> userEntityList = userService.selectUserInfo(userDto);
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }
}
