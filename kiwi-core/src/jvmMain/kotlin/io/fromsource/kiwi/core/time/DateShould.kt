package io.fromsource.kiwi.core.time

import io.fromsource.kiwi.core.BeComparable
import io.fromsource.kiwi.core.BeEqual
import java.time.DayOfWeek
import java.time.Month

interface DateShould<T : Comparable<T>> : BeEqual<T, DateShould<T>>, BeComparable<T, DateShould<T>> {

    fun beAfter(expected: T): DateShould<T>
    fun beBefore(expected: T): DateShould<T>

    fun haveSameYearAs(expected: T): DateShould<T>
    fun haveSameMonthAs(expected: T): DateShould<T>
    fun haveSameDayOfYearAs(expected: T): DateShould<T>
    fun haveSameDayOfWeekAs(expected: T): DateShould<T>
    fun haveSameEpochDayAs(expected: T): DateShould<T>

    fun beAtYear(year: Int): DateShould<T>
    fun beAtMonth(month: Month): DateShould<T>
    fun beAtDayOfTheYear(dayOfWeek: DayOfWeek): DateShould<T>
    fun haveDayOfWeek(dayOfWeek: DayOfWeek): DateShould<T>

    fun isInFirstQuoter(): DateShould<T>
    fun isInSecondQuoter(): DateShould<T>
    fun isInThirdQuoter(): DateShould<T>
    fun isInFourthQuoter(): DateShould<T>

    fun hasLeapYear(): DateShould<T>
}