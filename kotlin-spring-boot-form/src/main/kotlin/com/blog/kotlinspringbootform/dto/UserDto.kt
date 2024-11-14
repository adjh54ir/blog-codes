package com.blog.kotlinspringbootform.dto

import lombok.AccessLevel
import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor

/**
 * tb_user 테이블과 매핑되는 DTO 클래스입니다.
 *
 * @author : jonghoon
 * @fileName : UserDto
 * @since : 10/1/24
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
class UserDto @Builder(toBuilder = true) private constructor(// 사용자 시퀀스
    private val userSq: Int, // 사용자 아이디
    private val userId: String, // 사용자 패스워드
    private val userPw: String, // 사용자 이름
    private val userNm: String, userSt: String
)
