package com.blog.kotlinspringbootform.component.conditionExpComp

import org.springframework.stereotype.Component

@Component
class ConditionExpressionComponent {

    fun conditionIfExp(x: Int, y: Int, a: Int, b: Int) {

        // 사용예시 -1 : 반환 값이 존재하지 않는 경우
        if (a > b) {
            println("a가 b보다 큽니다")
        } else {
            println("a가 b보다 크지 않습니다")
        }

        // 사용예시 -2 : 반환 값이 존재하는 경우 1
        val result = if (x > 0) "양수" else if (x < 0) "음수" else "0"

        // 사용예시 -3 : 반환 값이 존재하는 경우 2
        val max = if (a > b) {
            println("a is larger")
            a
        } else {
            println("b is larger")
            b
        }

        // 사용예시 -4 : 삼항연산자를 사용하기
//        val resultVal1 = x > y ? a : b          // X 사용불가
        val resultVal2 = if (x > y) a else b
    }

    fun conditionWhenExp(x: Int, y: Int) {
        // 사용예시 -1 : x 값에 대한 분기 처리
        when (x) {
            1 -> println("x is 1")
            2 -> println("x is 2")
            else -> println("x is neither 1 nor 2")
        }

        // 사용예시 -2 : x 값에 대한 분기 처리 + 값 지정
        val result = when (x) {
            0, 1 -> "x is 0 or 1"
            in 2..10 -> "x is between 2 and 10"
            else -> "x is outside the range"
        }


        // 사용예시 -3 : 함수 자체에 대해 파라미터를 통한 분기 처리 및 값 반환
        fun checkType(x: Any) = when (x) {
            is Int -> println("x is an Integer")
            is String -> println("x is a String")
            is List<*> -> println("x is a List")
            else -> println("x is of unknown type")
        }

        // 사용예시 -4 : 함수를 호출하여 값을 출력합니다.
        checkType(5)           // 출력: x is an Integer
        checkType("Hello")     // 출력: x is a String
        checkType(listOf(1, 2, 3)) // 출력: x is a List
        checkType(3.14)        // 출력: x is of unknown type
    }
}