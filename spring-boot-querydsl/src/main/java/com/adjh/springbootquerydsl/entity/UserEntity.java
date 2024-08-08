package com.adjh.springbootquerydsl.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * tb_user 테이블과 매핑 객체
 *
 * @author : jonghoon
 * @fileName : UserEntity
 * @since : 2/8/24
 */
@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "tb_user")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("사용자 시퀀스")
    @Column(name = "user_sq")
    private long userSq;

    @Comment("사용자 아이디")
    @Column(name = "user_id")
    private String userId;

    @Comment("사용자 비밀번호")
    @Column(name = "user_pw")
    private String userPw;

    @Comment("사용자 이름")
    @Column(name = "user_nm")
    private String userNm;

    @Comment("사용자 상태")
    @Column(name = "user_st")
    private String userSt;

    @Comment("사용자 이메일")
    @Column(name = "user_email")
    private String userEmail;


    @Comment("등록일자")
    @Column(name = "reg_ts")
    @CreationTimestamp
    private Timestamp regTs;

    @Builder
    public UserEntity(long userSq, String userId, String userPw, String userNm, String userSt, String userEmail, Timestamp regTs) {
        this.userSq = userSq;
        this.userId = userId;
        this.userPw = userPw;
        this.userNm = userNm;
        this.userSt = userSt;
        this.userEmail = userEmail;
        this.regTs = regTs;
    }
}



