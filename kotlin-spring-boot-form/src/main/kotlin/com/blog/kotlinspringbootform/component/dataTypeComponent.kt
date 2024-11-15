package com.blog.kotlinspringbootform.component

import org.springframework.stereotype.Component

@Component
class dataTypeComponent {

    /**
     * 숫자 관련 변수들
     */
    fun numberType() {
        // Byte 예시
        val b: Byte = 100

        // Short 예시
        val s: Short = 10000

        // Int 예시
        val age: Int = 30

        // Long 예시
        val population: Long = 7000000000

        // UByte 예시
        val ub: UByte = 255u

        // UShort 예시
        val us: UShort = 65535u

        // UInt 예시
        val ui: UInt = 4294967295u

        // ULong 예시
        val ul: ULong = 18446744073709551615u

        // Float 예시
        val pi: Float = 3.14f

        // Double 예시
        val e: Double = 2.71828
    }

    /**
     * 진위형 관련 변수들
     */
    fun booleanType() {
        // Boolean 예시
        val isKotlinFun: Boolean = true
    }

    /**
     * 문자형 & 문자열형 & 배열형 관련 변수들
     */
    fun charType() {
        // Char 예시
        val initial: Char = 'A'

        // String 예시
        val name: String = "Kotlin"

        // Array 예시
        val numbers: Array<Int> = arrayOf(1, 2, 3)
    }

    /**
     * 기타 타입 관련 변수들
     */
    fun otherType() {
        // Any 예시
        val obj: Any = "Hello"

        // Nothing 예시
        fun fail(): Nothing = throw Exception("Failed")

        // Unit 예시
        fun printHello(): Unit {
            println("Hello")
        }
    }


}