package com.adjh.springbootquerydsl.dao.impl;

import com.adjh.springbootquerydsl.dao.DailyReportDao;
import com.adjh.springbootquerydsl.dto.DailyReportDto;
import com.adjh.springbootquerydsl.entity.*;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.Tuple;

import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.jpa.JPAExpressions.select;
import static org.hibernate.query.results.Builders.fetch;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : DailyReportDaoImpl
 * @since : 8/4/24
 */
public class DailyReportDaoImpl implements DailyReportDao {

    private final JPAQueryFactory queryFactory;

    private final QStudyDoStartEntity qStdDoStart = QStudyDoStartEntity.studyDoStartEntity;
    private final QStudyPlanEntity qStdPlan = QStudyPlanEntity.studyPlanEntity;
    private final QStudyEvalEntity qStdEval = QStudyEvalEntity.studyEvalEntity;
    private final QStudyDoEndEntity qStdDoEnd = QStudyDoEndEntity.studyDoEndEntity;

    public DailyReportDaoImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public DailyReportDto selectDailyStdyTimeAverage(DailyReportDto dailyReportDto) {
        return queryFactory
                .select(Projections.constructor(DailyReportDto.class,
                                (JPAExpressions
                                        .select(qStdPlan.planTm)
                                        .from(qStdDoStart)
                                        .innerJoin(qStdPlan)
                                        .on(qStdPlan.planSq.eq(qStdDoStart.planSq))
                                        .where(qStdPlan.userUuid.eq(dailyReportDto.getUserUuid())
                                                .and(qStdDoStart.strtDt.eq(dailyReportDto.getSearchTs())))
                                        .groupBy(qStdDoStart.planSq, qStdPlan.planTm))
                                        .as("planTm")     // 일일 계획한 시간
                                qStdDoEnd.stdyTm.sum().as("stdyTm"),
                                qStdDoEnd.purestdyTm.sum().as("pureStdyTm"),
                                qStdDoEnd.bststdyTm.sum().as("bstStdyTm"),
                                qStdDoEnd.avgAtntn.avg().floor().as("avgAtntn"),
                                qStdDoEnd.avgStrss.avg().floor().as("avgStrss")
                        )
                        .from(qStdEval)
                        .innerJoin(qStdDoEnd).on(qStdEval.doSq.eq(qStdDoEnd.doSq))
                        .innerJoin(qStdDoStart).on(qStdEval.doSq.eq(qStdDoStart.doSq))
                        .innerJoin(qStdPlan).on(qStdDoStart.planSq.eq(qStdPlan.planSq))
                        .where(qStdPlan.userUuid.eq(dailyReportDto.getUserUuid())
                                .and(qStdDoStart.strtDt.eq(dailyReportDto.getSearchTs())))
                        .fetchOne();
    }

    @Override
    public DailyReportDto selectPlanAchieve(DailyReportDto dailyReportDto) {
        return null;
    }

    @Override
    public List<DailyReportDto> selectDailyCondition(DailyReportDto dailyReportDto) {
        return null;
    }

    @Override
    public List<DailyReportDto> selectDailyStdyTime(DailyReportDto dailyReportDto) {
        return null;
    }

    @Override
    public List<DailyReportDto> selectDailyStdyPlan(DailyReportDto dailyReportDto) {
        return null;
    }

    @Override
    public DailyReportDto selectDtlStudy(DailyReportDto dailyReportDtlDto) {
        return null;
    }

    @Override
    public List<DailyReportDto> selectDtlStudyTime(DailyReportDto dailyReportDtlDto) {
        return null;
    }
}
