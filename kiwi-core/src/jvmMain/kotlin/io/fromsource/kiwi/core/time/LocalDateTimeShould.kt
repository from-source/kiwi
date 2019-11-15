package io.fromsource.kiwi.core.time

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.Month

class LocalDateTimeShould(private val actual: LocalDateTime): DateShould<LocalDateTime> {
    override fun actual() = actual

    override infix fun beAfter(expected: LocalDateTime) = beGreaterThan(expected)

    override infix fun beBefore(expected: LocalDateTime) = beLessThan(expected)

    override fun haveSameYearAs(expected: LocalDateTime): DateShould<LocalDateTime> {
        val message = "${actual()} should have the same year as $expected"
        assert(actual().year == expected.year) { message }
        return this
    }

    override fun haveSameMonthAs(expected: LocalDateTime): DateShould<LocalDateTime> {
        val message = "${actual()} should have the same month as $expected"
        assert(actual().month == expected.month) { message }
        return this
    }

    override fun haveSameDayOfYearAs(expected: LocalDateTime): DateShould<LocalDateTime> {
        val message = "${actual()} should have the same day of year as $expected"
        assert(actual().month == expected.month &&
                actual().dayOfMonth == expected.dayOfMonth) { message }
        return this
    }

    override fun haveSameDayOfWeekAs(expected: LocalDateTime): DateShould<LocalDateTime> {
        val message = "${actual()} should have the same day of week as $expected"
        assert(actual().dayOfWeek == expected.dayOfWeek) { message }
        return this
    }

    override fun haveSameEpochDayAs(expected: Long): DateShould<LocalDateTime> {
        val message = "${actual()} should have $expected epoch days"
        assert(actual().toLocalDate().toEpochDay() == expected) { message }
        return this
    }

    override fun beAtYear(expected: Int): DateShould<LocalDateTime> {
        val message = "${actual()} should be at $expected year"
        assert(actual().year == expected) { message }
        return this
    }

    override fun beAtMonth(expected: Month): DateShould<LocalDateTime> {
        val message = "${actual()} should be at $expected month"
        assert(actual().month== expected) { message }
        return this
    }

    override fun beDayOfTheYear(expected: Int): DateShould<LocalDateTime> {
        val message = "${actual()} should be $expected day of year"
        assert(actual().dayOfYear == expected) { message }
        return this
    }

    override fun beDayOfWeek(expected: DayOfWeek): DateShould<LocalDateTime> {
        val message = "${actual()} should be $expected day of week"
        assert(actual().dayOfWeek == expected) { message }
        return this
    }

    override fun beInQ1(): DateShould<LocalDateTime> {
        val message = "${actual()} should be in 1st quoter"
        assert(actual().month in Month.JANUARY .. Month.MARCH) { message }
        return this
    }

    override fun beInQ2(): DateShould<LocalDateTime> {
        val message = "${actual()} should be in 2nd quoter"
        assert(actual().month in Month.APRIL .. Month.JUNE) { message }
        return this
    }

    override fun beInQ3(): DateShould<LocalDateTime> {
        val message = "${actual()} should be in 3rd quoter"
        assert(actual().month in Month.JULY .. Month.SEPTEMBER) { message }
        return this
    }

    override fun beInQ4(): DateShould<LocalDateTime> {
        val message = "${actual()} should be in 4th quoter"
        assert(actual().month in Month.OCTOBER .. Month.DECEMBER) { message }
        return this
    }

    override fun beInLeapYear(): DateShould<LocalDateTime> {
        val message = "${actual()} should be in leap year"
        assert(actual().toLocalDate().isLeapYear) { message }
        return this
    }

}