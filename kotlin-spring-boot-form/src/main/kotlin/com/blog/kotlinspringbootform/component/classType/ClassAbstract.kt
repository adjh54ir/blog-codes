package com.blog.kotlinspringbootform.component.classType

/**
 * 추상 클래스 사용 예시
 *
 * @fileName      : AbstractClassComponent
 * @author        : jonghoon
 * @since         : 11/23/24
 */
abstract class ClassAbstract {
    abstract fun draw() // 추상 메서드

    fun moveTo(x: Int, y: Int) { // 일반 메서드
        println("Moving to ($x, $y)")
    }
}

class Circle : ClassAbstract() {
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
