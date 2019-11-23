package io.fromsource.kiwi.core.number

import io.fromsource.kiwi.core.should
import java.math.BigDecimal
import kotlin.test.Test

class BigDecimalShouldTest {

    @Test
    fun `should fail when numbers are not equaled`() {
        runCatching {
            more.should() beEqual less
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$more should == $less")
    }

    @Test
    fun `should guarantee numbers are equaled`() {
        more.should() beEqual more
    }

    @Test
    fun `should fail because negative is not positive`() {
        runCatching {
            negative.should().bePositive()
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$negative should be > 0")
    }

    @Test
    fun `should fail because zero is not positive`() {
        runCatching {
            zero.should().bePositive()
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$zero should be > 0")
    }

    @Test
    fun `should guarantee number is positive`() {
        positive.should().bePositive()
    }

    @Test
    fun `should fail because positive number is not negatives`() {
        runCatching {
            positive.should().beNegative()
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$positive should be < 0")
    }

    @Test
    fun `should fail because zero number is not negatives`() {
        runCatching {
            zero.should().beNegative()
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$zero should be < 0")
    }

    @Test
    fun `should guarantee number is negative`() {
        negative.should().beNegative()
    }

    @Test
    fun `should fail when number is not less than`() {
        runCatching {
            more.should() beLessThan less
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$more should be < $less")
    }

    @Test
    fun `should guarantee than number is less than`() {
        less.should() beLessThan more
    }

    @Test
    fun `should fail when number is not less or equal than`() {
        runCatching {
            more.should() beLessOrEqualThan less
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$more should be <= $less")
    }

    @Test
    fun `should fail when because number is not zero`() {
        runCatching {
            positive.should().beZero()
        }.should()
            .beFailure(AssertionError::class)
            .haveFailureMessage("$positive should == $zero")
    }

    @Test
    fun `should guarantee number is zero`() {
        zero.should().beZero()
    }

    companion object {
        private val more = BigDecimal.TEN
        private val less = BigDecimal.ONE

        private val zero = BigDecimal.ZERO
        private val negative = BigDecimal("-20.01")
        private val positive = BigDecimal("20.01")
    }
}