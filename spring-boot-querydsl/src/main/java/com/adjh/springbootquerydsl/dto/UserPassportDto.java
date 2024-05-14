package com.adjh.springbootquerydsl.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : UserPassportDto
 * @since : 2024. 5. 7.
 */
@Getter
@ToString
@NoArgsConstructor
public class UserPassportDto {
    private Long userSq;
    private String userId;
    private String userNm;
    private String userSt;
    private String passportSq;
    private String passportId;
    private String issueDate;
    private String expiredDate;


    @Builder
    public UserPassportDto(Long userSq, String userId, String userNm, String userSt, String passportSq, String passportId, String issueDate, String expiredDate) {
        this.userSq = userSq;
        this.userId = userId;
        this.userNm = userNm;
        this.userSt = userSt;
        this.passportSq = passportSq;
        this.passportId = passportId;
        this.issueDate = issueDate;
        this.expiredDate = expiredDate;
    }
}
