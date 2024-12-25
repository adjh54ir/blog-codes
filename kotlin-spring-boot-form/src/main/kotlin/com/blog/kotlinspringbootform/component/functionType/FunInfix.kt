package com.blog.kotlinspringbootform.component.functionType

/**
 * Infix 함수를 사용한 예시
 *
 * @fileName      : FunInfix
 * @author        : jonghoon
 * @since         : 11/23/24
 */
class FunInfix {
    class MyNumber(val value: Int) {
        infix fun add(other: MyNumber): MyNumber {
            return MyNumber(this.value + other.value)
        }
    }

    fun main() {
        val num1 = MyNumber(10)
        val num2 = MyNumber(20)

        // 일반적인 호출
        val result1 = num1.add(num2)

        // 중위 표기법 사용
        val result2 = num1 add num2

        println(result2.value) // 출력: 30
    }
}