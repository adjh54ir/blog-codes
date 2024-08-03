package com.adjh.multiflexapi.jpa.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;

/**
 * tb_user 테이블과 매핑 객체
 *
 * @author : jonghoon
 * @fileName : UserEntity
 * @since : 2/8/24
 */
@Getter
@Entity
@Table(name = "tb_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_sq")
    private int userSq;

    @Column(name = "user_id")
    private String userId;

    // 사용자 패스워드
    @Column(name = "user_pw")
    private String userPw;

    // 사용자 이름
    @Column(name = "user_nm")
    private String userNm;

    // 사용자 상태
    @Column(name = "user_st")
    private String userSt;

    @Column(name = "user_email")
    private String userEmail;

}