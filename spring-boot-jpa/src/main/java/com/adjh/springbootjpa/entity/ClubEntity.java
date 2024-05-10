package com.adjh.springbootjpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : ClubEntity
 * @since : 2024. 5. 8.
 */
@Entity
@Getter
@Table(name = "tb_club")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClubEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("동아리 시퀀스")
    @Column(name = "club_sq")
    private long clubSq;

    @Comment("동아리 이름")
    @Column(name = "club_nm")
    private String clubNm;

    @Comment("동아리 설명")
    @Column(name = "club_desc")
    private String clubDesc;


    @Comment("동아리 회장 이름")
    @Column(name = "club_captain_nm")
    private String clubCaptainNm;

    @CreationTimestamp
    @Comment("동아리 설립일자")
    @Column(name = "est_date")
    private Timestamp estDate;

}
