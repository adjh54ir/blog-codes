package com.adjh.springbootjpa.dto;

import lombok.*;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : UserDto
 * @since : 4/23/24
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {

    private Long userSq;
    private String userId;
    private String userNm;
    private String userSt;
    private String passportSq;
    private String passportId;
    private String issueDate;
    private String expiredDate;
    private String sortOptions;


    @Builder(toBuilder = true)
    public UserDto(Long userSq, String userId, String userNm, String userSt, String passportSq, String passportId, String issueDate, String expiredDate, String sortOptions) {
        this.userSq = userSq;
        this.userId = userId;
        this.userNm = userNm;
        this.userSt = userSt;
        this.passportSq = passportSq;
        this.passportId = passportId;
        this.issueDate = issueDate;
        this.expiredDate = expiredDate;
        this.sortOptions = sortOptions;
    }
}
