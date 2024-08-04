package com.adjh.springbootquerydsl.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : StudyDoEndEntity
 * @since : 8/4/24
 */

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tb_stdy_do_end")
public class StudyDoEndEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "do_sq")
    private int doSq;           // 실행 시퀀스

    @Column(name = "end_dt")
    private Time endDt;         // 종료 일시

    @Column(name = "end_dttm")
    private Date endDttm;       // 종료 일자

    @Column(name = "stdy_tm")
    private Time stdyTm;        // 공부 시간

    @Column(name = "pure_stdy_tm")
    private Time purestdyTm;    // 순공 시간

    @Column(name = "bst_stdy_tm")
    private Time bststdyTm;     // 열공 시간

    @Column(name = "max_atntn")
    private int maxAtntn;       // 최대 집중력

    @Column(name = "avg_atntn")
    private int avgAtntn;       // 평균 집중력

    @Column(name = "avg_strss")
    private String avgStrss;    // 평균 스트레스

    @Column(name = "reg_ts")
    private Timestamp regTs;    // 등록 타임 스탬프
}
