package com.adjh.springbootshort.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : UserDto
 * @since : 6/6/24
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    private String userId;
    private String userNm;
    private String userPw;
    private String userSt;

    @Builder(toBuilder = true)
    public UserDto(String userId, String userNm, String userPw, String userSt) {
        this.userId = userId;
        this.userNm = userNm;
        this.userPw = userPw;
        this.userSt = userSt;
    }
}
