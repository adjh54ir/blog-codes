package com.adjh.springboottest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : UserDto
 * @since : 4/23/24
 */
@Getter
@ToString
@NoArgsConstructor
public class UserDto {

    private int userSq;
    private String userId;
    private String userNm;
    private String userSt;


    @Builder(toBuilder = true)
    public UserDto(int userSq, String userId, String userNm, String userSt) {
        this.userSq = userSq;
        this.userId = userId;
        this.userNm = userNm;
        this.userSt = userSt;
    }
}
