package com.blog.kotlinspringbootform.component

import com.blog.kotlinspringbootform.model.dto.UserDto
import com.blog.kotlinspringbootform.model.enum.EnumDayOfWeek
import com.blog.kotlinspringbootform.model.enum.EnumDirection
import org.springframework.stereotype.Component


@Component
class ClassComponent() {

    var email: String = ""


    /**
     * 데이터 클래스를 호출합니다.
     */
    fun dataClassCall() {

        val user: UserDto

        // setter
        val userDto: UserDto = UserDto(userSq = 1, userNm = "lee", userId = "adjh54", userSt = "S", userPw = "password")

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

    fun enumClassCall() {
        // EnumDirection 사용 예시
        val direction = EnumDirection.NORTH
        println("Selected direction: $direction")                   // Selected direction: NORTH

        // EnumDayOfWeek 사용 예시
        val today = EnumDayOfWeek.WEDNESDAY
        println("Today is: $today")                                 // Today is: WEDNESDAY
        println("Today's value: ${today.value}")                    // Today's value: 4
        println("Is today a weekend? ${today.isWeekend()}")         // Is today a weekend? false

        val saturday = EnumDayOfWeek.SATURDAY
        println("Is Saturday a weekend? ${saturday.isWeekend()}")   // Is Saturday a weekend? true
    }


}