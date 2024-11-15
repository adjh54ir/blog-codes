package com.blog.kotlinspringbootform.component

import org.springframework.stereotype.Component

@Component
class CollectionComponent {

    /**
     * 초기화, 읽기만 가능한 Collection
     */
    fun readCollection(username: String, password: String) {
        // Set 초기화
        val readOnlySet = setOf(1, 2, 3)

        // List 초기화
        val readOnlyList = listOf("apple", "banana", "cherry")

        // Map 초기화
        val readOnlyMap = mapOf("a" to 1, "b" to 2, "c" to 3)

        // 이후에는 이 컬렉션들을 변경할 수 없습니다.
        // readOnlyList.add("date") // 컴파일 에러
    }

    /**
     * 초기화, 읽기, 수정, 삭제가 가능한 Collection
     */
    fun mutableCollection() {
        // MutableSet 사용
        val mutableSet = mutableSetOf(1, 2, 3)
        mutableSet.add(4) // 가능

        // MutableList 사용
        val mutableList = mutableListOf("apple", "banana", "cherry")
        mutableList.add("date") // 가능

        // MutableMap 사용
        val mutableMap = mutableMapOf("a" to 1, "b" to 2)
        mutableMap["c"] = 3 // 가능
    }

    /**
     * Collection 빈 상태로 선언합니다.
     */
    fun emptyCollection() {
        val emptySet = emptySet<Int>();
        val emptyList = emptyList<String>();
        val emptyMap = emptyMap<String, Int>()
    }

    /**
     * Collection Build를 이용하여 선언 및 값을 초기화합니다.
     */
    fun buildCollection() {
        val map = buildMap { //
            put("a", 1)
            put("b", 0)
            put("c", 4)
        }
        println(map)

        val list = buildList {
            add("hello")
            add("world")
        }

        val set = buildSet {
            add("hello")
            add("world")
        }
    }

}