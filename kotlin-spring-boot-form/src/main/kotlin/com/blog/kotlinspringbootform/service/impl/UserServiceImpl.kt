package com.blog.kotlinspringbootform.service.impl

import com.blog.kotlinspringbootform.model.dto.UserDto
import com.blog.kotlinspringbootform.service.BoardService
import com.blog.kotlinspringbootform.service.UserService

class UserServiceImpl : UserService, BoardService {
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

    override fun selectBoardList(any: Any): Any {
        TODO("Not yet implemented")
    }

    override fun insertBoard(any: Any): Int {
        TODO("Not yet implemented")
    }

    override fun updateBoard(any: Any): Int {
        TODO("Not yet implemented")
    }

    override fun deleteBoard(any: Any): Int {
        TODO("Not yet implemented")
    }
}