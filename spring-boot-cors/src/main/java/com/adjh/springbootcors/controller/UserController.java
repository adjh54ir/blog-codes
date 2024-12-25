package com.adjh.springbootcors.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserController
 * @since : 10/26/24
 */
@Slf4j
@Controller
@RequestMapping("api/v1/user")
@CrossOrigin(origins = "http://localhost:3000",
        methods = {RequestMethod.GET, RequestMethod.POST},
        maxAge = 3600,
        allowedHeaders = "*")
public class UserController {
    /**
     * [API] 사용자 리스트 조회
     *
     * @return ResponseEntity
     */

    @PostMapping("/user")
    public ResponseEntity<Object> selectUser() {
        List<Object> resultList = new ArrayList<>();
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    /**
     * [API] 사용자 리스트 조회
     *
     * @return ResponseEntity
     */
    @PostMapping("/login")
    public ResponseEntity<Object> login() {
        Object resultObj = new Object();
        return new ResponseEntity<>(resultObj, HttpStatus.OK);
    }
}
