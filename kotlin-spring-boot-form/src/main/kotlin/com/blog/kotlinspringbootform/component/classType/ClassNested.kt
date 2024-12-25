package com.blog.kotlinspringbootform.component.classType

/**
 * 중첩 클래스 사용 예시
 *
 * @fileName      : ClassNested
 * @author        : jonghoon
 * @since         : 11/23/24
 */
class ClassNested {
    private val outerProperty = "Outer Property"

    class NestedClass {
        fun nestedMethod() {
            println("This is a nested class method")
            // 오류: outerProperty에 직접 접근 불가
            // println(outerProperty)
        }
    }

    fun outerMethod() {
        val nested = NestedClass()
        nested.nestedMethod()
    }
}

fun main() {
    val nestedInstance = ClassNested.NestedClass()
    nestedInstance.nestedMethod()

    val outerInstance = ClassNested()
    outerInstance.outerMethod()
}