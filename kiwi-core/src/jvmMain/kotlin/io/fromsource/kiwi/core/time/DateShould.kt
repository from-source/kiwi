package io.fromsource.kiwi.core.time

import io.fromsource.kiwi.core.BeComparable
import io.fromsource.kiwi.core.BeEqual
import java.time.DayOfWeek
import java.time.Month

interface DateShould<T : Comparable<T>> : BeEqual<T, DateShould<T>>, BeComparable<T, DateShould<T>> {

    infix fun beAfter(expected: T): DateShould<T>
    infix fun beBefore(expected: T): DateShould<T>

    infix fun haveSameYearAs(expected: T): DateShould<T>
    infix fun haveSameMonthAs(expected: T): DateShould<T>
    infix fun haveSameDayOfYearAs(expected: T): DateShould<T>
    infix fun haveSameDayOfWeekAs(expected: T): DateShould<T>
    infix fun haveSameEpochDayAs(expected: Long): DateShould<T>

    infix fun beAtYear(expected: Int): DateShould<T>
    infix fun beAtMonth(expected: Month): DateShould<T>
    infix fun beDayOfTheYear(expected: Int): DateShould<T>
    infix fun beDayOfWeek(expected: DayOfWeek): DateShould<T>

    fun beInQ1(): DateShould<T>
    fun beInQ2(): DateShould<T>
    fun beInQ3(): DateShould<T>
    fun beInQ4(): DateShould<T>

    fun beInLeapYear(): DateShould<T>
}