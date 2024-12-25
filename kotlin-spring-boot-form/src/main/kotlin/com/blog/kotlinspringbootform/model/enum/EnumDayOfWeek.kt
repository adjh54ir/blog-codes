package com.blog.kotlinspringbootform.model.enum

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