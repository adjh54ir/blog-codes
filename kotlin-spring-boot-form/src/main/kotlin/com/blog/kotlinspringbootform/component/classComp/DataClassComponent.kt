package com.blog.kotlinspringbootform.component.classComp

data class DataClassComponent(
    val userSq: Int,    // 사용자 시퀀스
    val userId: String, // 사용자 아이디
    val userPw: String, // 사용자 패스워드
    val userNm: String, // 사용자 이름
    val userSt: String, // 사용자 상태
)

/**
 * 데이터 클래스를 호출합니다.
 */
fun dataClassCall() {

    val user: DataClassComponent

    // setter
    val userDto: DataClassComponent = DataClassComponent(userSq = 1, userNm = "lee", userId = "adjh54", userSt = "S", userPw = "password")

    // getter
    userDto.also {
        println("userSq : ${it.userSq}")
        println("userNm : ${it.userNm}")
        println("userId : ${it.userId}")
        println("userPw : ${it.userPw}")
    }

    // toString
    println("userDto : ${userDto.toString()}")
}