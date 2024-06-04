package com.adjh.springbootcommon.dto;

import lombok.*;

import java.sql.Date;
import java.sql.Time;

/**
 * java.sql.* 패키지를 이용한 Date 다루기
 *
 * @author : jonghoon
 * @fileName : DataTypeSqlDto
 * @since : 6/4/24
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DateTypeSqlDto {
    // 날짜 시퀀스
    private int dateSq;

    // 날짜 타입
    private Date dateType;

    // 시간 타입
    private Time timeType;

    // 연도 타입
    private int yearType;

    // 타임스템프 타입
    private Date timestampType;

    // 타임스탬프 타입(밀리세컨드)
    private Date timestampMilsecType;

    // 타임스탬프 타입(마이크로세컨드)
    private Date timestampMicrosecType;

    @Builder(toBuilder = true)
    public DateTypeSqlDto(int dateSq, Date dateType, Time timeType, int yearType, Date timestampType, Date timestampMilsecType, Date timestampMicrosecType) {
        this.dateSq = dateSq;
        this.dateType = dateType;
        this.timeType = timeType;
        this.yearType = yearType;
        this.timestampType = timestampType;
        this.timestampMilsecType = timestampMilsecType;
        this.timestampMicrosecType = timestampMicrosecType;
    }
}
