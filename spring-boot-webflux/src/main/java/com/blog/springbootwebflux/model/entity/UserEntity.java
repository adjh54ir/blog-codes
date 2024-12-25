package com.blog.springbootwebflux.model.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : UserEntity
 * @since : 2024. 12. 9.
 */
@Getter
@ToString
@Table("tb_user")                                   // 실제 테이블명 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    private Long userSq;            // 사용자 시퀀스
    private String userEmail;       // 사용자 이메일
    private String userId;          // 사용자 아이디
    private String userNm;          // 사용자 이름
    private String userSt;          // 사용자 상태
    private String userPw;          // 사용자 패스워드
    private LocalDateTime regTs = LocalDateTime.now();    // 등록일자

    @Builder(toBuilder = true)
    public UserEntity(Long userSq, String userEmail, String userId, String userNm, String userSt, String userPw, LocalDateTime regTs) {
        this.userSq = userSq;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userNm = userNm;
        this.userSt = userSt;
        this.userPw = userPw;
        this.regTs = regTs;
    }
}
