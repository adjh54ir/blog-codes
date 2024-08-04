package com.adjh.springbootquerydsl.dao;

import com.adjh.springbootquerydsl.dto.DailyReportDto;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : DailyReport
 * @since : 8/4/24
 */
@Repository
public interface DailyReportDao {

    // 일일 공부시간 / 평균 점수 조회
    DailyReportDto selectDailyStdyTimeAverage(DailyReportDto dailyReportDto);

    // 계획 달성도
    DailyReportDto selectPlanAchieve(DailyReportDto dailyReportDto);

    // 하루동안 나의 기분
    List<DailyReportDto> selectDailyCondition(DailyReportDto dailyReportDto);

    // 일일 공부시간 그래프
    List<DailyReportDto> selectDailyStdyTime(DailyReportDto dailyReportDto);

    // 일일 공부계획
    List<DailyReportDto> selectDailyStdyPlan(DailyReportDto dailyReportDto);

    // 상세 공부 내역
    DailyReportDto selectDtlStudy(DailyReportDto dailyReportDtlDto);

    // 상세 공부 내역 - 상세 공부시간
    List<DailyReportDto> selectDtlStudyTime(DailyReportDto dailyReportDtlDto);
}
