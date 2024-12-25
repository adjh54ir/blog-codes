package com.adjh.springbootquerydsl.dto;

import lombok.*;

import java.time.LocalDateTime;

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

    private Long userSq;
    private String userId;
    private String userNm;
    private String userSt;
    private LocalDateTime userDt;


    @Builder(toBuilder = true)
    public UserDto(Long userSq, String userId, String userNm, String userSt, LocalDateTime userDt) {
        this.userSq = userSq;
        this.userId = userId;
        this.userNm = userNm;
        this.userSt = userSt;
        this.userDt = userDt;
    }
}
