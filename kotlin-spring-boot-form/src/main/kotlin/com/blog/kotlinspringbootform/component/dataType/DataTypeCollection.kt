package com.blog.kotlinspringbootform.component.dataType

import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

/**
 * Collection Data Type 데이터 예시
 *
 * @fileName      : DataTypeCollection
 * @author        : jonghoon
 * @since         : 11/23/24
 */
@Component
class DataTypeCollection {

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

    /**
     * Collection Type 구현체를 사용하는 예시
     */
    fun collectionImplements() {

        val arrayList = ArrayList<String>()  // 동적 크기 조정이 가능한 리스트
        arrayList.add("첫 번째")
        arrayList.add("두 번째")

        val linkedList = LinkedList<Int>()   // 이중 연결 리스트
        linkedList.add(1)
        linkedList.add(2)

        // Set 예제
        val hashSet = HashSet<String>()      // 해시 테이블 기반 Set
        hashSet.add("사과")
        hashSet.add("사과")  // 중복된 값은 추가되지 않음

        val treeSet = TreeSet<Int>()         // 정렬된 Set
        treeSet.add(3)
        treeSet.add(1)
        treeSet.add(2)
        println(treeSet)  // [1, 2, 3] 순서로 출력

        // Map 예제
        val hashMap = HashMap<String, Int>()  // 해시 테이블 기반 Map
        hashMap["one"] = 1
        hashMap["two"] = 2

        val treeMap = TreeMap<String, Int>()  // 정렬된 Map
        treeMap["b"] = 2
        treeMap["a"] = 1
        treeMap["c"] = 3
        println(treeMap)  // 키를 기준으로 정렬되어 출력
    }


}