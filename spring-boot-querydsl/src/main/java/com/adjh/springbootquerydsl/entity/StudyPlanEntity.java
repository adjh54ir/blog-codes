package com.adjh.springbootquerydsl.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : StudyPlanEntity
 * @since : 8/4/24
 */

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_stdy_plan")
public class StudyPlanEntity {

    // 계획 시퀀스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_sq")
    private int planSq;

    // 사용자 UUID
    @Column(name = "user_uuid")
    private String userUuid;

    // 계획 이름
    @Column(name = "plan_nm")
    private String planNm;

    // 계획 시간
    @Column(name = "plan_tm")
    private Time planTm = Time.valueOf("00:00:00");

    // 요일 비트
    @Column(name = "day_bit")
    private String dayBit;

    // 활성화 여부
    @Column(name = "actv_yn")
    private int actvYn;

    // 삭제 여부
    @Column(name = "del_yn")
    private int delYn;

    // 변경 타임스탬프
    @Column(name = "mod_ts")
    private String modTs;

    // 날짜 파라미터용 [홈화면]
    @Transient
    private String dailyStdyDate;


//    // 학습 상세 계획리스트 [홈화면]
//    @OneToMany(mappedBy = "studyPlan", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<StudyTimeList> studyTimeList;
}
