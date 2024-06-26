package com.adjh.springboot3tierform.model.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : DateDto
 * @since : 24. 5. 31.
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DateDto {
    // 날짜 시퀀스
    private int dateSq;

    // 날짜 타입
    private LocalDateTime dateType;

    // 시간 타입
    private LocalDateTime timeType;

    // 연도 타입
    private LocalDateTime yearType;

    // 타임스템프 타입
    private LocalDateTime timestampType;

    // 타임스탬프 타입(밀리세컨드)
    private LocalDateTime timestampMilsecType;

    // 타임스탬프 타입(마이크로세컨드)
    private LocalDateTime timestampMicrosecType;

    @Builder
    public DateDto(int dateSq, LocalDateTime dateType, LocalDateTime timeType, LocalDateTime yearType, LocalDateTime timestampType, LocalDateTime timestampMilsecType, LocalDateTime timestampMicrosecType) {
        this.dateSq = dateSq;
        this.dateType = dateType;
        this.timeType = timeType;
        this.yearType = yearType;
        this.timestampType = timestampType;
        this.timestampMilsecType = timestampMilsecType;
        this.timestampMicrosecType = timestampMicrosecType;
    }
}


