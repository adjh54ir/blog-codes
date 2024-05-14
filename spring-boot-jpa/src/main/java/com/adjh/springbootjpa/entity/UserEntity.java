package com.adjh.springbootjpa.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.io.Serializable;

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

    @Comment("사용자 이름")
    @Column(name = "user_nm")
    private String userNm;

    @Comment("사용자 상태")
    @Column(name = "user_st")
    private String userSt;


    @Builder(toBuilder = true)
    public UserEntity(long userSq, String userId, String userNm, String userSt) {
        this.userSq = userSq;
        this.userId = userId;
        this.userNm = userNm;
        this.userSt = userSt;
    }
}


