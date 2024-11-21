package com.blog.kotlinspringbootform.service.impl

import com.blog.kotlinspringbootform.model.dto.UserDto
import com.blog.kotlinspringbootform.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {

    // 추상 메서드 구현
    override fun selectUserList(userDto: UserDto): List<UserDto> {
        TODO("Not yet implemented")
    }

    override fun insertUser(userDto: UserDto): Int {
        TODO("Not yet implemented")
    }

    override fun updateUser(userDto: UserDto): Int {
        TODO("Not yet implemented")
    }

    override fun deleteUserList(userDto: List<UserDto>) {
        TODO("Not yet implemented")
    }
}