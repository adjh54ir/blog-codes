package com.blog.kotlinspringbootform.component.functionType

/**
 * Tailrec 함수 사용 예시
 *
 * @fileName      : FunTailrec
 * @author        : jonghoon
 * @since         : 11/23/24
 */
class FunTailrec {
    // 일반적인 재귀 함수
    tailrec fun factorial(n: Long, accumulator: Long = 1): Long {
        return when (n) {
            0L -> accumulator
            else -> factorial(n - 1, n * accumulator)
        }
    }

    fun main() {
        println(factorial(5)) // 출력: 120
    }
}