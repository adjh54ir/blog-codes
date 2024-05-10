package com.adjh.springbootjpa.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : PassportEntity
 * @since : 4/16/24
 */
@Entity
@Table(name = "tb_passport")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PassportEntity {

    // 여권 시퀀스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passport_sq")
    private Long passportSq;

    // 여권 번호
    @Column(name = "passport_id")
    private String passportId;

    // 사용자 시퀀스
    @Column(name = "user_sq")
    private Long userSq;

    // 발급일
    @Column(name = "issue_date")
    private String issueDate;

    // 만료일
    @Column(name = "expired_date")
    private String expiredDate;

}
