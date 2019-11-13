package io.fromsource.kiwi.core.time

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.Month

class LocalDateShould(private val actual: LocalDate) : DateShould<LocalDate> {
    override fun actual() = actual

    override infix fun beAfter(expected: LocalDate) = beGreaterThan(expected)

    override infix fun beBefore(expected: LocalDate) = beLessThan(expected)

    override infix fun haveSameYearAs(expected: LocalDate): DateShould<LocalDate> {
        val message = "${actual()} should have the same year as $expected"
        assert(actual().year == expected.year) { message }
        return this
    }

    override infix fun haveSameMonthAs(expected: LocalDate): DateShould<LocalDate> {
        val message = "${actual()} should have the same month as $expected"
        assert(actual().month == expected.month) { message }
        return this
    }

    override infix fun haveSameDayOfYearAs(expected: LocalDate): DateShould<LocalDate> {
        val message = "${actual()} should have the same day of year as $expected"
        assert(actual().month == expected.month &&
                actual().dayOfMonth == expected.dayOfMonth) { message }
        return this
    }

    override infix fun haveSameDayOfWeekAs(expected: LocalDate): DateShould<LocalDate> {
        val message = "${actual()} should have the same day of week as $expected"
        assert(actual().dayOfWeek == expected.dayOfWeek) { message }
        return this
    }

    override fun haveSameEpochDayAs(expected: LocalDate): DateShould<LocalDate> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun beAtYear(year: Int): DateShould<LocalDate> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun beAtMonth(month: Month): DateShould<LocalDate> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun beAtDayOfTheYear(dayOfWeek: DayOfWeek): DateShould<LocalDate> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun haveDayOfWeek(dayOfWeek: DayOfWeek): DateShould<LocalDate> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isInFirstQuoter(): DateShould<LocalDate> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isInSecondQuoter(): DateShould<LocalDate> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isInThirdQuoter(): DateShould<LocalDate> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isInFourthQuoter(): DateShould<LocalDate> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasLeapYear(): DateShould<LocalDate> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
