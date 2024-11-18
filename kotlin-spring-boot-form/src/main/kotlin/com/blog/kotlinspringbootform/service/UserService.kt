package com.blog.kotlinspringbootform.service

import com.blog.kotlinspringbootform.model.dto.UserDto
import org.springframework.stereotype.Service

@Service
interface UserService {

    // 추상 메서드
    fun selectUserList(userDto: UserDto): List<UserDto>

    // 추상 메서드
    fun insertUser(userDto: UserDto): Int

    // 추상 메서드
    fun updateUser(userDto: UserDto): Int

    // 추상 메서드
    fun deleteUserList(userDto: List<UserDto>)

    // 디폴트 메서드
    fun showDefaultMethod(userDto: UserDto) {
        println("default Method...")
    }
}

