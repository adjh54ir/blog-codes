package com.blog.kotlinspringbootform.component.classComp

class InnerClassComponent {
    private val message: String = "Hello from Outer"

    inner class InnerClass {
        fun greet() = println(message)
    }

    /**
     * 내부 클래스 호출 사용예시
     */
    fun innerClassCall() {
        val outer = ClassComponent()
        val inner = outer.InnerClass()
        inner.greet()  // 출력: Hello from Outer
    }
}