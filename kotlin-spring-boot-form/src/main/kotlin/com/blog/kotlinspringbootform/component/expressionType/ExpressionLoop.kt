package com.blog.kotlinspringbootform.component.expressionType

/**
 * 루프 사용예시
 *
 * @fileName      : ExpressionLoop
 * @author        : jonghoon
 * @since         : 11/23/24
 */
class ExpressionLoop {
    /**
     * for문 반복문을 사용한 예시
     */
    fun forLoop() {

        // 범위를 이용한 for 루프-1 : 1이상 5이하 출력
        for (i in 1..5) {
            println(i)      // 출력: 1, 2, 3, 4, 5
        }

        // 범위를 이용한 for 루프-2 : 1이상 5이하 출력
        val range = 1..5
        for (i in range) {
            println(i)      // 출력: 1, 2, 3, 4, 5
        }

        // 범위를 이용한 for 루프-3 : 1이상 5미만 출력
        for (i in 1 until 5) {
            println(i)      // 출력: 1, 2, 3, 4
        }

        // 범위를 이용한 for 루프-4 : 5이하 1이상 출력(역순출력)
        for (i in 5 downTo 1) {
            println(i)      // 출력: 5, 4, 3, 2, 1
        }

        // 범위를 이용한 for 루프-5 : 1부터 시작해서 2씩 증가하면서 10 이하의 모든 값(1, 3, 5, 7, 9)이 출력됩니다.
        for (i in 1..10 step 2) {
            println(i)      // 출력: 1, 3, 5, 7, 9
        }

        // 범위를 이용한 for 루프-6 : 10부터 시작해서 -2씩 감소하면서 10 이하의 모든 값(10, 8, 6, 4, 2)이 출력됩니다.
        for (i in 10 downTo 1 step 2) {
            println(i)  // 10, 8, 6, 4, 2 출력
        }

        // 범위를 이용한 for 루프 -> 1이상 5미만 출력
        for (i in 1 until 5) {
            println(i)  // 1, 2, 3, 4 출력
        }


        // 컬렉션을 이용한 for 루프
        val fruits = listOf("apple", "banana", "cherry")
        for (fruit in fruits) {
            println(fruit)
        }

        // 인덱스와 함께 사용
        for ((index, value) in fruits.withIndex()) {
            println("$index: $value")
        }
    }


    /*
     * while 문을 활용한 예시
     */
    fun whileLoop() {
        var i = 0
        while (i < 5) {
            println(i)
            i++
        }
    }

    /*
    * do - while 문을 활용한 예시
    */
    fun doWhileLoop() {
        var i = 0
        do {
            println(i)
            i++
        } while (i < 5)
    }

    /**
     * 반복문을 제어하는 예시 :continue, break
     */
    fun ctrlLoop() {
        for (i in 1..10) {
            if (i == 3) continue  // 3일 때 다음 반복으로 건너뜁니다
            if (i > 5) break      // 5보다 크면 반복문을 종료합니다
            println(i)
        }
    }
}