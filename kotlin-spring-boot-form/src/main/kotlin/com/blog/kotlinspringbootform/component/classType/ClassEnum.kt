package com.blog.kotlinspringbootform.component.classType

/**
 * enum 클래스 사용 예시
 *
 * @fileName      : ClassEnum
 * @author        : jonghoon
 * @since         : 11/23/24
 */
enum class ClassEnum {
}

/**
 * 상수로 값을 지정한 ENUM CLASS
 */
enum class EnumDirection {
    NORTH, SOUTH, EAST, WEST
}

/**
 * 일자를 숫자로 가지고 있습니다.
 */
enum class EnumDayOfWeek(val value: Int) {
    SUNDAY(1),
    MONDAY(2),
    TUESDAY(3),
    WEDNESDAY(4),
    THURSDAY(5),
    FRIDAY(6),
    SATURDAY(7);

    fun isWeekend(): Boolean {
        return this == SATURDAY || this == SUNDAY
    }
}

fun enumClassCall() {
    // EnumDirection 사용 예시
    val direction = EnumDirection.NORTH
    println("Selected direction: $direction")                   // Selected direction: NORTH

    // EnumDayOfWeek 사용 예시
    val today = EnumDayOfWeek.WEDNESDAY
    println("Today is: $today")                                 // Today is: WEDNESDAY
    println("Today's value: ${today.value}")                    // Today's value: 4
    println("Is today a weekend? ${today.isWeekend()}")         // Is today a weekend? false

    val saturday = EnumDayOfWeek.SATURDAY
    println("Is Saturday a weekend? ${saturday.isWeekend()}")   // Is Saturday a weekend? true
}