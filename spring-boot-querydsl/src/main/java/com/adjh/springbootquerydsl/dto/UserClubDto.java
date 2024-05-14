package com.adjh.springbootquerydsl.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : UserClubDto
 * @since : 2024. 5. 8.
 */
@Getter
@ToString
@NoArgsConstructor
public class UserClubDto {

    private Long userSq;
    private String userId;
    private String userNm;
    private String userSt;
    private Long clubSq;
    private String clubNm;
    private String clubDesc;
    private Timestamp estDate;

    @Builder(toBuilder = true)
    public UserClubDto(Long userSq, String userId, String userNm, String userSt, Long clubSq, String clubNm, String clubDesc, Timestamp estDate) {
        this.userSq = userSq;
        this.userId = userId;
        this.userNm = userNm;
        this.userSt = userSt;
        this.clubSq = clubSq;
        this.clubNm = clubNm;
        this.clubDesc = clubDesc;
        this.estDate = estDate;
    }
}
