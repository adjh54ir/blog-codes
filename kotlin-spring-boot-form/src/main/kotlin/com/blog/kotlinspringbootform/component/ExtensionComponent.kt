package com.blog.kotlinspringbootform.component

import org.springframework.stereotype.Component

/**
 * 상속 관련한 컴포넌트
 */
@Component
class ExtensionComponent {

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