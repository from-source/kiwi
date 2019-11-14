package io.fromsource.kiwi.core.time

import io.fromsource.kiwi.core.should
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate
import java.time.LocalDate.now
import java.util.stream.Stream
import kotlin.test.Test

class LocalDateShouldTest {

    @Test
    fun `should failed because dates are not equal`() {
        runCatching {
            today.should() beEqual tomorrow
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$today should == $tomorrow")
    }

    @Test
    fun `should guarantee dates are equal`() {
        val date = yesterday.plusDays(1)

        today.should() beEqual date
    }

    @Test
    fun `should failed because date is not before`() {
        runCatching {
            today.should() beBefore yesterday
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$today should be < $yesterday")
    }

    @Test
    fun `should guarantee date is before`() {
        today.should() beBefore tomorrow
    }

    @Test
    fun `should failed because date is not after`() {
        runCatching {
            today.should() beAfter tomorrow
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$today should be > $tomorrow")
    }

    @Test
    fun `should guarantee date is after`() {
        today.should() beAfter yesterday
    }

    @MethodSource("notBetween")
    @ParameterizedTest
    fun `should fail because date is not between (from, to)`(date: LocalDate, from: LocalDate, to: LocalDate) {
        runCatching {
            date.should().beBetween(from, to)
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$date should be between ($from .. $to)")
    }

    @Test
    fun `should guarantee than date is between`() {
        today.should().beBetween(yesterday, tomorrow)
    }

    @Test
    fun `should failed because date does not have the same year`() {
        runCatching {
            today.should() haveSameYearAs todayOneYearLater
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$today should have the same year as $todayOneYearLater")
    }

    @Test
    fun `should guarantee dates have the same year`() {
        today.should() haveSameYearAs beginningOfYear
    }

    @Test
    fun `should failed because date does not have the same month`() {
        runCatching {
            today.should() haveSameMonthAs todayOneMonthLater
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$today should have the same month as $todayOneMonthLater")
    }

    @Test
    fun `should guarantee dates have the same month`() {
        today.should() haveSameMonthAs beginningOfMonth
    }

    @Test
    fun `should failed because date does not have the same day of year`() {
        runCatching {
            today.should() haveSameDayOfYearAs yesterday
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$today should have the same day of year as $yesterday")
    }

    @Test
    fun `should guarantee dates have the same day of year`() {
        val nonLeapYear = LocalDate.of(2019, 11, 14)
        val nonLeapYearNeither = LocalDate.of(2018, 11, 14)
        nonLeapYear.should() haveSameDayOfYearAs nonLeapYearNeither
    }

    @Test
    fun `should guarantee dates have the same day even if one of them is leap year`() {
        val nonLeapYear = LocalDate.of(2019, 11, 14)
        val leapYear = LocalDate.of(2020, 11, 14)
        nonLeapYear.should() haveSameDayOfYearAs leapYear
    }

    @Test
    fun `should failed because date does not have the same day of week`() {
        runCatching {
            today.should() haveSameDayOfWeekAs yesterday
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$today should have the same day of week as $yesterday")
    }

    @Test
    fun `should guarantee dates have the same day of week`() {
        today.should() haveSameMonthAs todayOneYearLater
    }

    @Test
    fun `should failed because date does not have expected days of epoch`() {
        runCatching {
            today.should() haveSameEpochDayAs yesterday.toEpochDay()
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$today should have ${yesterday.toEpochDay()} epoch days")
    }

    @Test
    fun `should guarantee dates have the expected days of epoch`() {
        today.should() haveSameEpochDayAs today.toEpochDay()
    }

    @Test
    fun `should failed because date is not at given year`() {
        runCatching {
            today.should() beAtYear todayOneYearLater.year
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$today should be at ${todayOneYearLater.year} year")
    }

    @Test
    fun `should guarantee date is at given year`() {
        today.should() beAtYear today.year
    }

    @Test
    fun `should failed because date is not at given month`() {
        runCatching {
            today.should() beAtMonth todayOneMonthLater.month
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$today should be at ${todayOneMonthLater.month} month")
    }

    @Test
    fun `should guarantee date is at given month`() {
        today.should() beAtMonth beginningOfMonth.month
    }

    @Test
    fun `should failed because date does not have expected number of days in year`() {
        runCatching {
            today.should() beDayOfTheYear today.dayOfYear + 1
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$today should be ${today.dayOfYear + 1} day of year")
    }

    @Test
    fun `should guarantee date has expected numer of days in year`() {
        today.should() beDayOfTheYear today.dayOfYear
    }

    @Test
    fun `should failed because date does not have expected number of days in week`() {
        runCatching {
            today.should() beDayOfWeek today.dayOfWeek + 1
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$today should be ${today.dayOfWeek + 1} day of week")
    }

    @Test
    fun `should guarantee date has expected numer of days`() {
        today.should() beDayOfWeek today.dayOfWeek
    }

    @Test
    fun `should failed because date is not in leap year`() {
        runCatching {
            nonLeapYear.should().beInLeapYear()
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$nonLeapYear should be in leap year")
    }

    @Test
    fun `should guarantee date has in leap year`() {
        leapYear.should().beInLeapYear()
    }

    companion object {
        val today = now()
        val yesterday = today.minusDays(1)
        val tomorrow = today.plusDays(1)
        val dayAfterTomorrow = today.plusDays(2)

        val beginningOfMonth = today.withDayOfMonth(1)
        val beginningOfYear = today.withDayOfYear(1)

        val todayOneYearLater = today.plusYears(1)
        val todayOneMonthLater = today.plusMonths(1)

        val leapYear = LocalDate.of(2020, 11, 14)
        val nonLeapYear = leapYear.minusYears(1)

        @JvmStatic
        fun notBetween(): Stream<Arguments> =
            Stream.of(
                Arguments.of(today, today, tomorrow),
                Arguments.of(yesterday, today, tomorrow),
                Arguments.of(tomorrow, today, tomorrow),
                Arguments.of(dayAfterTomorrow, today, tomorrow)
            )
    }
}