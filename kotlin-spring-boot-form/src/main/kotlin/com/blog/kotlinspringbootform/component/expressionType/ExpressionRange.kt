package com.blog.kotlinspringbootform.component.expressionType

import java.time.LocalDate
import java.time.LocalTime

/**
 * Range 표현식 사용 예시
 *
 * @fileName      : ExpressionRange
 * @author        : jonghoon
 * @since         : 11/23/24
 */
class ExpressionRange {

    operator fun ClosedRange<LocalDate>.iterator() = object : Iterator<LocalDate> {
        var current = start
        override fun hasNext() = current <= endInclusive
        override fun next() = current.also { current = it.plusDays(1) }
    }

    operator fun ClosedRange<LocalTime>.iterator() = object : Iterator<LocalTime> {
        var current = start
        override fun hasNext() = current <= endInclusive
        override fun next() = current.also { current = it.plusSeconds(1) }
    }

    fun range() {

        val intRange = 1..5 // 1부터 5까지의 정수 범위
        val charRange = 'a'..'z' // 'a'부터 'z'까지의 문자 범위
        val dateRange = LocalDate.of(2024, 1, 1)..LocalDate.of(2024, 12, 31)
        val timeRange = LocalTime.of(9, 0)..LocalTime.of(17, 0)

        for (i in intRange) {
            println(i) // 1, 2, 3, 4, 5 출력
        }

        for (i in charRange) {
            println(i) // 1, 2, 3, 4, 5 출력
        }

        for (i in dateRange) {
            println(i) // 1, 2, 3, 4, 5 출력
        }

        for (i in timeRange) {
            println(i) // 1, 2, 3, 4, 5 출력
        }
    }

    fun rangeOperation() {
        // until: 끝 값을 포함하지 않는 범위
        for (i in 1 until 5) {
            println(i) // 1, 2, 3, 4 출력
        }

        // step: 증가 값 지정
        for (i in 1..10 step 2) {
            println(i) // 1, 3, 5, 7, 9 출력
        }

        // downTo: 역순 범위
        for (i in 5 downTo 1) {
            println(i) // 5, 4, 3, 2, 1 출력
        }

        // 범위 연산자
        val a = 1..<5   // 1, 2, 3, 4, 5를 포함하는 범위
        val b = 5 downTo 1  // 5, 4, 3, 2, 1을 포함하는 범위
        val c = 1 until 5   // 1, 2, 3, 4를 포함하는 범위 (5는 제외)
        val d = 1..5 step 2 // 1, 3, 5를 포함하는 범위
    }

    fun rangeAttrOperation() {

        val range = 1..5
        println(range.first) // 1
        println(range.last) // 5
        println(range.isEmpty()) // false
        println(3 in range) // true

    }


}