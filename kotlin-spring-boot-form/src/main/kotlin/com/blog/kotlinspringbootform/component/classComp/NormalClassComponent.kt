package com.blog.kotlinspringbootform.component.classComp

class NormalClassComponent {
}

// 클래스 선언 방법-1: 클래스 헤더가 존재하지 않는 형태
class Person {
    // 클래스 본문
    var name: String = ""
    var age: Int = 0

    init {
        println("Person instance created with name: $name")
    }
}

// 클래스 선언 방법-2: 클래스 헤더(매개변수)가 존재하는 경우
class Student(private val id: Int, private var name: String) {

    init {
        println("Person instance created with name: $name")
    }

    // 클래스 본문
    fun introduce() {
        println("My name is $name and my student ID is $id")
    }
}

// 클래스 선언 방법-3: 본문이 없는 경우
class EmptyClass;

fun classCall() {
    val p = Person()
    val student = Student(1, "John")
    val EmptyClass = Student(0, "John")
}
