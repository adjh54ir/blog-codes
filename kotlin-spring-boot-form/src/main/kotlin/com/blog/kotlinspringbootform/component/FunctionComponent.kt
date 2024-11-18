package com.blog.kotlinspringbootform.component

import org.springframework.stereotype.Component

/**
 * Please explain the class!!
 *
 * @fileName      : FunctionComponent
 * @author        : jonghoon
 * @since         : 11/17/24
 */

@Component
class FunctionComponent {

    /**
     * 일반 함수 구조 : 파라미터, 리턴타입 정의
     */
    fun normalFun(parameter1: String, parameter2: Int): String {
        val result = parameter1 + parameter2
        // 함수 본문
        return result
    }

    /**
     * 단일 함수 구조 : 하나의 리턴 값으로 연산만 수행한다.
     */
    fun singleFun(x: Int): Int = x * 2

    /**
     * 확장 함수 구조
     */
    fun String.addExclamation() = this + "!"

    /**
     * 확장 함수 호출 : String 클래스를 확장하여 addExclamation를 추가하였습니다. 이에 대해 호출을 하여 기존의 클래스에 함수를 추가합니다.
     */
    fun extensionFunCall() {
        val str = "Hello"
        println(str.addExclamation())  // 출력: Hello!
    }

    /**
     * 고차함수 구조
     */
    fun higherOrderFun(x: Int, y: Int, op: (Int, Int) -> Int): Int = op(x, y)

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


    /**
     * 일반적인 타입을 지정한 파라미터
     */
    fun normalParam(name: String): String {
        return "hello $name"
    }

    /**
     * default 값을 가지는 파라미터
     */
    fun defaultParam(name: String = "lee"): String {
        return "hello $name"
    }

    /**
     * vararg 파라미터를 이용하여 가변 인자 함수
     */
    fun varargSumParam(vararg numbers: Int): Int = numbers.sum()

    /**
     * vararg 파라미터를 이용하여 가변 인자 함수 호출
     */
    fun varargParamCall() {
        varargSumParam()                      // 인자 없이 호출 가능
        varargSumParam(1)           // 단일 인자
        varargSumParam(1, 2, 3)     // 여러 인자
        varargSumParam(*intArrayOf(1, 2, 3))  // 배열을 전개 연산자(*)와 함께 사용
    }

}