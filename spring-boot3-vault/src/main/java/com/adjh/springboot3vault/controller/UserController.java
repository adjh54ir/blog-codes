package com.adjh.springboot3vault.controller;

import com.adjh.springboot3vault.dto.UserDto;
import com.adjh.springboot3vault.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 사용자 PK(userSq)로 조회
     */
    @PostMapping("/{userSq}")
    public ResponseEntity<UserDto> findByUserSq(@PathVariable Long userSq) {
        UserDto userDto = userService.findByUserSq(userSq);
        return ResponseEntity.ok(userDto);
    }

    /**
     * 사용자 ID로 조회
     */
    @PostMapping("/userId")
    public ResponseEntity<UserDto> findByUserId(@RequestParam String userId) {

        UserDto userDto = userService.findByUserId(userId);
        return ResponseEntity.ok(userDto);
    }

    /**
     * 전체 사용자 조회
     */
    @GetMapping("/user")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.selectUser());
    }
}
