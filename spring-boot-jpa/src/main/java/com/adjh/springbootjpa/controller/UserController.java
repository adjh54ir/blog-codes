package com.adjh.springbootjpa.controller;

import com.adjh.springbootjpa.dao.UserDao;
import com.adjh.springbootjpa.dto.UserClubDto;
import com.adjh.springbootjpa.dto.UserDto;
import com.adjh.springbootjpa.dto.UserPassportDto;
import com.adjh.springbootjpa.entity.UserEntity;
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
 * @since : 4/26/24
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }


    @PostMapping("/userPassports")
    public ResponseEntity<UserPassportDto> selectUserPassport(@RequestBody UserDto userDto) {
        UserPassportDto result = userDao.selectUserPassport(userDto);
        System.out.println("결과값 :: " + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/userClubs")
    public ResponseEntity<List<UserClubDto>> selectUserOrderList(@RequestBody UserDto userDto) {
        List<UserClubDto> result = userDao.selectUserClubList(userDto);
        System.out.println("결과값 :: " + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/userClubsRight")
    public ResponseEntity<List<UserClubDto>> selectUserOrderListRight(@RequestBody UserDto userDto) {
        List<UserClubDto> result = userDao.selectUserClubListRight(userDto);
        System.out.println("결과값 :: " + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/userInfo")
    public ResponseEntity<List<UserEntity>> selectUserInfo(@RequestBody UserDto userDto) {
        List<UserEntity> result = userDao.selectUserInfo(userDto);
        System.out.println("결과값 :: " + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/userClubAll")
    public ResponseEntity<List<UserEntity>> selectUserClubAll(@RequestBody UserDto userDto) {
        List<UserEntity> result = userDao.selectUserClubAllList(userDto);
        System.out.println("결과값 :: " + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
