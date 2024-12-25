package com.blog.springbootwebflux.model.dto;

import lombok.*;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@ToString
@Table("tb_user")                                   // 실제 테이블명 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {

    private Long userSq;        // 사용자 시퀀스
    private String userId;      // 사용자 아이디
    private String userNm;      // 사용자 이름
    private String userSt;      // 사용자 상태

    @Builder(toBuilder = true)
    public UserDto(Long userSq, String userId, String userNm, String userSt) {
        this.userSq = userSq;
        this.userId = userId;
        this.userNm = userNm;
        this.userSt = userSt;
    }
}
