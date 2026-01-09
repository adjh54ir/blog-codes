package com.adjh.springboot3vault.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserDto {

    private Long userSq;
    private String userEmail;
    private String userId;
    private String userNm;
    private String userPw;
    private String userSt;
    private LocalDateTime regTs;
}
