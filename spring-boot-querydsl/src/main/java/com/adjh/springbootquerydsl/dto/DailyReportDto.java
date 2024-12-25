package com.adjh.springbootquerydsl.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : DailyReportDto
 * @since : 8/4/24
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DailyReportDto {
    // ***** 공통으로 사용되는 파라미터 ******
    private String userUuid;            // 사용자 uuid;
    private Date searchTs;              // 조회 날짜

    // 일일 공부시간 / 평균 점수
    private Time planTm;
    private Time stdyTm;
    private Time purestdyTm;
    private Time bststdyTm;
    private int avgAtntn;
    private int avgstrss;

    // 계획 달성도
    private int totalAchvScore;     // 성취율

    // 하루동안 나의 기분
    private String emtnCd;          // 정서코드
    private String emtnNm;          // 정서코드 명
    private int emtnPer;            // 정서코드 퍼센트

    // 일일 공부시간 그래프
    private int doSq;               // 실행 시퀀스
    private String doNm;            // 실행 명
    private LocalDateTime strtDttm;     // 시작일시
    private LocalDateTime endDttm;      // 종료일시
    private String clrNm;           // 색상css

    // 일일 공부계획
    private int planSq;             // 계획 시퀀스
    private String planNm;          // 계획 이름
    private String stdyStatus;      // 학습 상태
}
