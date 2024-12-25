package com.adjh.springboottest.dto;

import lombok.*;

import java.sql.Timestamp;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : CodeDto
 * @since : 6/16/24
 */


@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CodeDto {

    private String grpCd;

    private String cd;

    private String grpCdNm;

    private String cdNm;

    private int sortOrder;

    private Timestamp regDt;

    private boolean useYn;

    private String welcomeMsg;

    @Builder(toBuilder = true)
    public CodeDto(String grpCd, String cd, String grpCdNm, String cdNm, int sortOrder, Timestamp regDt, boolean useYn, String welcomeMsg) {
        this.grpCd = grpCd;
        this.cd = cd;
        this.grpCdNm = grpCdNm;
        this.cdNm = cdNm;
        this.sortOrder = sortOrder;
        this.regDt = regDt;
        this.useYn = useYn;
        this.welcomeMsg = welcomeMsg;
    }
}

