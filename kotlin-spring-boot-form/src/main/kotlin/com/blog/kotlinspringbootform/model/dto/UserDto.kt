package com.blog.kotlinspringbootform.model.dto

/**
 * tb_user 테이블과 매핑되는 DTO 클래스입니다.
 *
 * @author : jonghoon
 * @fileName : UserDto
 * @since : 10/1/24
 */
data class UserDto(
    val userSq: Int,    // 사용자 시퀀스
    val userId: String, // 사용자 아이디
    val userPw: String, // 사용자 패스워드
    val userNm: String, // 사용자 이름
    val userSt: String, // 사용자 상태
)
