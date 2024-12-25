package com.blog.kotlinspringbootform.component.functionType

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * Suspend 함수 예시
 *
 * @fileName      : FunSuspend
 * @author        : jonghoon
 * @since         : 11/23/24
 */
class FunSuspend {
    // suspend 함수 예시
    private suspend fun fetchUserData(): User {
        delay(1000) // 1초 대기
        return User("John", 25)
    }

    // 코루틴 내에서 suspend 함수 사용
    fun main() = runBlocking {
        val user = fetchUserData() // suspend 함수 호출
        println(user)
    }

    data class User(
            val name: String,
            val age: Int
    )
}