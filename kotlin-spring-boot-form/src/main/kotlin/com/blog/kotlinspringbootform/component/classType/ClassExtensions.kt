package com.blog.kotlinspringbootform.component.classType

import org.springframework.stereotype.Component

/**
 * 상속 관련 예시
 *
 * @fileName      : ClassExtensions
 * @author        : jonghoon
 * @since         : 11/23/24
 */
@Component
class ClassExtensions {

    /**
     * 상속 예정 클래스
     */
    open class Animal(val name: String) {
        open fun makeSound() {
            println("The animal makes a sound")
        }
    }


    /**
     * Animal 클래스로 부터 상속을 받은 클래스
     */
    class Dog(name: String) : Animal(name) {
        override fun makeSound() {
            println("The dog barks")
        }
    }
}