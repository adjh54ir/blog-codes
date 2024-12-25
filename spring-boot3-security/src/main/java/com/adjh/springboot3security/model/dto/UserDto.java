package com.adjh.springboot3security.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * tb_user 테이블과 매핑되는 DTO 클래스입니다.
 *
 * @author : jonghoon
 * @fileName : UserDto
 * @since : 10/1/24
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {

    // 사용자 시퀀스
    private int userSq;

    // 사용자 아이디
    private String userId;

    // 사용자 패스워드
    private String userPw;

    // 사용자 이름
    private String userNm;

    // 사용자 상태
    private String userSt = "S";


    @Builder(toBuilder = true)
    private UserDto(int userSq, String userId, String userPw, String userNm, String userSt) {
        this.userSq = userSq;
        this.userId = userId;
        this.userPw = userPw;
        this.userNm = userNm;
        this.userSt = userSt;
    }

}
