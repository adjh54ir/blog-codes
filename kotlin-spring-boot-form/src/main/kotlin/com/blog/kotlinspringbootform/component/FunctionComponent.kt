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


}