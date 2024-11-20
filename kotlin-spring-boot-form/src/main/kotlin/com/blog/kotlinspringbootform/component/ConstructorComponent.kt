package com.blog.kotlinspringbootform.component

// 생성자 선언 방법-1: 클래스 이름과 매개변수 뒤에 constructor 선언하는 경우
class ConstructorComponent constructor(firstName: String) {
    init {
        // 생성자 초기화 수행
    }
}

// 생성자 선언 방법-2: constructor를 사용하지 않는 경우
class ConstructorComponent2(firstName: String, lastName: String, age: Int) {
    init {
        // 생성자 초기화 수행
    }
}


// 생성자 선언 방법-3 : 주 생성자와 보조 생성자가 존재하는 경우
class Person(val name: String, var age: Int) {
    // 주 생성자

    init {
        // 생성자 초기화 수행
    }

    constructor(name: String) : this(name, 0) {
        // 보조 생성자
    }

}

// 아래와 같은 구조를 통해 객체 생성 시 유연성을 제공합니다.
val person1 = Person("Alice", 30)  // 주 생성자 사용
val person2 = Person("Bob")        // 보조 생성자 사용, age는 0으로 초기화됨

