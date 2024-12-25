package com.blog.kotlinspringbootform.component.classType

/**
 * 내부 클래스 사용 예시
 *
 * @fileName      : ClassInner
 * @author        : jonghoon
 * @since         : 11/23/24
 */
class ClassInner {
    private val message: String = "Hello from Outer"

    inner class InnerClass2 {
        fun greet() = println(message)
    }

    /**
     * 내부 클래스 호출 사용예시
     */
    fun innerClassCall() {
        val outer = ClassInner()
        val inner = outer.InnerClass2()
        inner.greet()  // 출력: Hello from Outer
    }
}