package com.blog.kotlinspringbootform.component.functionType

/**
 * 고차 함수를 정의합니다.
 *
 * @fileName      : FunHigherOrder
 * @author        : jonghoon
 * @since         : 11/23/24
 */
class FunHigherOrder {

    /**
     * 고차함수 구조
     */
    private fun higherOrderFun(x: Int, y: Int, op: (Int, Int) -> Int): Int = op(x, y)

    /**
     * 고차함수 호출 : 함수 파라미터
     */
    fun higherOrderFunCall() {
        // 덧셈 연산
        val sum = higherOrderFun(5, 3) { a, b -> a + b }
        println(sum)  // 출력: 8

        // 곱셈 연산
        val product = higherOrderFun(5, 3) { a, b -> a * b }
        println(product)  // 출력: 15
    }

    /**
     * 인라인 고차함수 구조
     */
    private inline fun inlineHigherOrderFun(x: Int, y: Int, op: (Int, Int) -> Int): Int = op(x, y)

    /**
     * 인라인 고차함수 호출 : 함수 파라미터
     */
    fun inlineHigherOrderFunCall() {
        // 덧셈 연산
        val sum = higherOrderFun(5, 3) { a, b -> a + b }
        println(sum)  // 출력: 8

        // 곱셈 연산
        val product = higherOrderFun(5, 3) { a, b -> a * b }
        println(product)  // 출력: 15
    }
}