package io.fromsource.kiwi.core.time

import io.fromsource.kiwi.core.should
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate
import java.time.LocalDate.now
import java.util.stream.Stream

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

    companion object {
        val today = now()
        val yesterday = today.minusDays(1)
        val tomorrow = today.plusDays(1)
        val dayAfterTomorrow = today.plusDays(2)

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