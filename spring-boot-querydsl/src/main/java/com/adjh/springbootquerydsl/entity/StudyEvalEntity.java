package com.adjh.springbootquerydsl.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Please explain the class!!
 *
 * @author : jonghoon
 * @fileName : StudyEvalEntity
 * @since : 8/4/24
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_stdy_eval")
public class StudyEvalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plan_sq", nullable = false)
    private int planSq;

    @Column(name = "do_sq", nullable = false)
    private int doSq;

    @Column(name = "eval_emtn", nullable = false)
    private String evalEmtn;

    @Column(name = "stdy_engmt", nullable = false)
    private String stdyEngmt;

    @Column(name = "stdy_lvl", nullable = false)
    private String stdyLvl;

    @Column(name = "stdy_type", nullable = false)
    private String stdyType;

    @Column(name = "achv_score", nullable = false)
    private int achvScore;

    @Column(name = "stdy_rcds", nullable = false)
    private String stdyRcds;
}
