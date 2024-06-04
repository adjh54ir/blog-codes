package com.adjh.springbootcommon.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;

/**
 * java.time.* 패키지를 이용한 Date 다루기
 *
 * @author : jonghoon
 * @fileName : DataTypeTimeDto
 * @since : 6/4/24
 */
public class DateTypeTimeDto {
    // 날짜 시퀀스
    private int dateSq;

    // 날짜 타입
    private LocalDate dateType;

    // 시간 타입
    private LocalTime timeType;

    // 연도 타입
    private Year yearType;

    // 타임스템프 타입
    private LocalDateTime timestampType;

    // 타임스탬프 타입(밀리세컨드)
    private LocalDateTime timestampMilsecType;

    // 타임스탬프 타입(마이크로세컨드)
    private LocalDateTime timestampMicrosecType;

    @Builder(toBuilder = true)

    public DateTypeTimeDto(int dateSq, LocalDate dateType, LocalTime timeType, Year yearType, LocalDateTime timestampType, LocalDateTime timestampMilsecType, LocalDateTime timestampMicrosecType) {
        this.dateSq = dateSq;
        this.dateType = dateType;
        this.timeType = timeType;
        this.yearType = yearType;
        this.timestampType = timestampType;
        this.timestampMilsecType = timestampMilsecType;
        this.timestampMicrosecType = timestampMicrosecType;
    }
}
