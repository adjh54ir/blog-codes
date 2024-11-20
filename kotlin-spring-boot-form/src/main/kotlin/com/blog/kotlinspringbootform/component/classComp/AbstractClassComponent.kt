package com.blog.kotlinspringbootform.component.classComp

abstract class AbstractClassComponent {
    abstract fun draw() // 추상 메서드

    fun moveTo(x: Int, y: Int) { // 일반 메서드
        println("Moving to ($x, $y)")
    }
}

class Circle : AbstractClassComponent() {
    override fun draw() {
        println("Drawing a circle")
    }
}

fun main() {
    // 사용 예
    val circle = Circle()
    circle.draw()    // 출력: Drawing a circle
    circle.moveTo(10, 20) // 출력: Moving to (10, 20)
}
