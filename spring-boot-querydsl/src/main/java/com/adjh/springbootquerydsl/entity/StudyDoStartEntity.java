package com.adjh.springbootquerydsl.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : StudyDoStartEntity
 * @since : 8/4/24
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_stdy_do_start")
public class StudyDoStartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "do_sq")
    private int doSq;           // 실행 시퀀스

    @Column(name = "plan_sq")
    private int planSq;         // 계획 시퀀스

    @Column(name = "user_uuid")
    private String userUuid;    // 사용자 UUID

    @Column(name = "do_nm")
    private String doNm;        // 실행 이름

    @Column(name = "strt_dt")
    private Date strtDt;        // 시작 일자

    @Column(name = "strt_dttm")
    private Date strtDttm;      // 시작 일자

    @Column(name = "strt_emtn")
    private String strtEmtn;    // 시작 정서

    @Column(name = "reg_ts")
    private Timestamp regTs;    // 등록 타임 스탬프
}
