package com.blog.kotlinspringbootform.component.functionType

/**
 * Please explain the class!!
 *
 * @fileName      : FunParamNormal
 * @author        : jonghoon
 * @since         : 11/23/24
 */
class FunParamNormal {
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
     * optional 파라미터를 사용한 함수
     */
    private fun optionalParam(name: String, age: Int?) {
        if (age != null) {
            println("$name is $age years old")
        } else {
            println("$name's age is unknown")
        }
    }

    fun optionalParamCall() {
        optionalParam("John", 25)    // age 값 제공
        optionalParam("Jane", null)  // age 값을 null로 제공
    }

    /**
     * vararg 파라미터를 이용하여 가변 인자 함수
     */
    private fun varargSumParam(vararg numbers: Int): Int = numbers.sum()

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