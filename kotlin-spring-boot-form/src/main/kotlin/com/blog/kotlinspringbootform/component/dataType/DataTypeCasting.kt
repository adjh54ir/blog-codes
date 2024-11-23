package com.blog.kotlinspringbootform.component.dataType

import org.springframework.stereotype.Component

/**
 * 데이터 타입 캐스팅 사용 예시
 *
 * @fileName      : DataTypeCasting
 * @author        : jonghoon
 * @since         : 11/23/24
 */
@Component
class DataTypeCasting {

    /**
     * 기본 타입의 캐스팅 방법
     */
    fun normalCasting() {
        val intNum = 10
        val longNum = 1000L
        val floatNum = 3.14f

        // Int를 다른 타입으로 변환
        val byteNum: Byte = intNum.toByte()
        val shortNum: Short = intNum.toShort()
        val longFromInt: Long = intNum.toLong()
        val floatFromInt: Float = intNum.toFloat()
        val doubleFromInt: Double = intNum.toDouble()
        val charFromInt: Char = intNum.toChar()
        val stringFromInt: String = intNum.toString()

        // Long을 Int로 변환
        val intFromLong: Int = longNum.toInt()

        // Float를 Int로 변환
        val intFromFloat: Int = floatNum.toInt()
    }

    /**
     * 안전하지 않은 as 캐스팅
     */
    fun asCasting() {
        val obj: Any = "Hello"
        val str: String = obj as String // "Hello"
        val num: Int = obj as Int // ClassCastException

    }

    /**
     * 안전한 as 캐스팅
     */
    fun asSafeCasting() {
        val obj: Any = "Hello"
        val str: String? = obj as? String // "Hello"
        val num: Int? = obj as? Int // null
    }


    /**
     * is 캐스팅
     */
    fun isCasting() {
        val obj: Any = "Hello"
        if (obj is String) {
            println(obj.length) // obj는 자동으로 String으로 캐스트됨
        }
    }

}