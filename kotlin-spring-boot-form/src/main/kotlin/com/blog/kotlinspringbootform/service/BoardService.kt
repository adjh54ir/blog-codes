package com.blog.kotlinspringbootform.service

import org.springframework.stereotype.Service

@Service
interface BoardService {
    fun selectBoardList(any: Any): Any
    fun insertBoard(any: Any): Int
    fun updateBoard(any: Any): Int
    fun deleteBoard(any: Any): Int
}