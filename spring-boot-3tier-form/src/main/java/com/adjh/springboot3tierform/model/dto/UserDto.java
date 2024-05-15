package com.adjh.springboot3tierform.model.dto;

import lombok.*;

/**
 * 사용자 DTO
 *
 * @author : jonghoon
 * @fileName : UserDto
 * @since : 5/15/24
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {

    private Long userSq;
    private String userId;
    private String userNm;
    private String userSt;


    @Builder(toBuilder = true)
    public UserDto(Long userSq, String userId, String userNm, String userSt) {
        this.userSq = userSq;
        this.userId = userId;
        this.userNm = userNm;
        this.userSt = userSt;
    }
}
