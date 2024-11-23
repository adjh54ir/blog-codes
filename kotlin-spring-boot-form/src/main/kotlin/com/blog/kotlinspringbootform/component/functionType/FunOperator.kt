package com.blog.kotlinspringbootform.component.functionType

/**
 * Operator 함수 예시
 *
 * @fileName      : FunOperator
 * @author        : jonghoon
 * @since         : 11/23/24
 */
class FunOperator {
    class Point(var x: Int, var y: Int) {
        // + 연산자 오버로딩
        operator fun plus(other: Point): Point {
            return Point(x + other.x, y + other.y)
        }

        // - 연산자 오버로딩
        operator fun minus(other: Point): Point {
            return Point(x - other.x, y - other.y)
        }
    }

    fun main() {
        val p1 = Point(10, 20)
        val p2 = Point(30, 40)

        // 연산자 사용
        val sum = p1 + p2      // plus() 함수 호출
        val diff = p1 - p2     // minus() 함수 호출

        println("sum: (${sum.x}, ${sum.y})")     // 출력: sum: (40, 60)
        println("diff: (${diff.x}, ${diff.y})")   // 출력: diff: (-20, -20)
    }
}