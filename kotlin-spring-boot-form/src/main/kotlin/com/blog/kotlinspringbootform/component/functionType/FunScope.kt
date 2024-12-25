package com.blog.kotlinspringbootform.component.functionType

import org.springframework.stereotype.Component


data class Person(var name: String, var age: Int)

/**
 * 범위 함수의 사용예시를 관리하는 컴포넌트
 */
class FunScope {


    /**
     * let 함수를 이용한 예시
     */
    fun letScopeFun() {
        val person: Person = Person("홍길동", 30)

        // [사용예시-1] 객체 내에서 let을 이용하여 변수를 처리하는 예시
        person.let {
            println("이름: ${it.name}")
            println("나이: ${it.age}")
            it.age += 1                     // 객체 내 변수의 값을 변환하여 반환합니다.
            println("1년 후 나이: ${it.age}")
        }

        // [사용예시-2] 객체 내에서 let을 이용하여 변수의 null 처리를 하는 예시
        val nullablePerson: Person? = null
        val result = nullablePerson?.let { "${it.name}은(는) ${it.age}살입니다." } ?: "Person 객체가 null입니다."
        println(result)        // null 인 경우 => Person 객체가 null입니다. / null이 아닌 경우 => 홍길동은(는) 30살입니다.


        // [사용예시-3] 리스트 내에서 let 함수를 이용하여 필터링하여 값을 반환받는 예시 : 문자 길이가 3초과값 반환
        val numbers = mutableListOf("one", "two", "three", "four", "five")
        val resultList = numbers.map { it.length }.filter { it > 3 }
        println(resultList)     // "three"(5), "four"(4), "five"(4)의 길이가 선택되어 [4, 5, 5]가 출력

    }


    /**
     * run 함수를 이용한 예시
     */
    fun runScopeFun() {

        // 1. 객체 초기화와 계산을 동시에 수행
        val result = run {
            val person = Person("홍길동", 30)      // 객체 초기화
            person.age += 1                                 // 객체 반환값 계산
            "${person.name}은(는) ${person.age}살입니다."     // run 함수 반환 값
        }
        println(result)  // 출력: 홍길동은(는) 31살입니다.

        // 2. 객체의 멤버 함수를 여러 번 호출
        val stringBuilder = StringBuilder()             // 객체 초기화
        val length = stringBuilder.run {
            append("Hello")                             // 객체 반환값 계산
            append(" ")                                 // 객체 반환값 계산
            append("World")                             // 객체 반환값 계산
            length                                      // run 함수 반환 값
        }
        println(stringBuilder.toString())  // 출력: Hello World
        println("길이: $length")  // 출력: 길이: 11
    }

    /**
     * with 함수를 이용한 예시
     */
    fun withScopeFun() {
        val person = Person("홍길동", 30)

        // 1. 객체의 여러 속성을 한 번에 설정
        val result = with(person) {
            name = "김철수"
            age = 25
            "이름: $name, 나이: $age"
        }
        println(result)  // 출력: 이름: 김철수, 나이: 25

        // 2. 복잡한 계산을 수행
        val numbers = listOf(1, 2, 3, 4, 5)
        val sum = with(numbers) {
            val doubledNumbers = map { it * 2 }
            doubledNumbers.sum()
        }
        println("합계: $sum")  // 출력: 합계: 30
    }


    /**
     * apply 함수를 이용한 예시
     */
    fun applyScopeFun() {
        // 1. 객체 초기화
        val person = Person("홍길동", 30).apply {
            name = "김철수"
            age = 25
        }
        println("이름: ${person.name}, 나이: ${person.age}")  // 출력: 이름: 김철수, 나이: 25

        // 2. 복잡한 객체 구성
        val stringBuilder = StringBuilder().apply {
            append("Hello")
            append(" ")
            append("World")
            insert(0, "Greeting: ")
        }
        println(stringBuilder.toString())  // 출력: Greeting: Hello World
    }

    /**
     * also 함수를 이용한 예시
     */
    fun alsoScopeFun() {
        // 1. 객체 초기화 후 로깅
        val person = Person("홍길동", 30).also {
            println("생성된 Person 객체: 이름=${it.name}, 나이=${it.age}")
        }

        // 2. 컬렉션 처리
        val numbers = mutableListOf(3, 1, 4, 1, 5, 9).also {
            it.sort()
            println("정렬된 리스트: $it")
        }

        println("Person 객체: $person")
        println("Numbers: $numbers")
    }

}