package com.adjh.springbootjpa.controller;

import com.adjh.springbootjpa.entity.UserEntity;
import com.adjh.springbootjpa.service.UserJpaRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * JPARepository 를 활용한 Controller
 *
 * @author : lee
 * @fileName : UserJpaRepositoryController
 * @since : 2024. 5. 10.
 */
@RequestMapping("/api/v1/userRepo")
@RestController
public class UserJpaRepositoryController {

    private final UserJpaRepositoryService userService;

    public UserJpaRepositoryController(UserJpaRepositoryService userService) {
        this.userService = userService;
    }


    /**
     * @return
     */
    @PostMapping("/users")
    public ResponseEntity<Object> selectUserList() {
        List<UserEntity> userEntityList = userService.userList();
        return new ResponseEntity<>(userEntityList, HttpStatus.OK);
    }

    /**
     * 사용자 등록
     *
     * @param userEntity
     * @return
     */
    @PostMapping("/user")
    public ResponseEntity<Object> userSave(@RequestBody UserEntity userEntity) {
        UserEntity result = userService.saveUser(userEntity);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 사용자 수정
     *
     * @param userEntity
     * @return
     */
    @PutMapping("/user")
    public ResponseEntity<Object> updateUser(@RequestBody UserEntity userEntity) {
        UserEntity result = userService.updateUser(userEntity);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 사용자 삭제
     *
     * @param userSq
     * @return
     */
    @DeleteMapping("/user")
    public ResponseEntity<Object> deleteUser(@RequestParam long userSq) {
        userService.deleteUserByUserSq(userSq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
