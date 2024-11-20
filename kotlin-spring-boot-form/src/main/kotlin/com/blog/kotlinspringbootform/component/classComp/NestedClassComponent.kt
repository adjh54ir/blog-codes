package com.blog.kotlinspringbootform.component.classComp

class NestedClassComponent {
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
    val nestedInstance = NestedClassComponent.NestedClass()
    nestedInstance.nestedMethod()

    val outerInstance = NestedClassComponent()
    outerInstance.outerMethod()
}