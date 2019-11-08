package io.fromsource.kiwi.core.number

import io.fromsource.kiwi.core.should
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

class DoubleShouldTest {
    private val more = 10.0
    private val less = 1.0

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

    @ParameterizedTest
    @ValueSource(doubles = [negative, zero])
    fun `should fail when number is not positive`(nonPositive: Double) {
        runCatching {
            nonPositive.should().bePositive()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$nonPositive should be > 0")
    }

    @Test
    fun `should guarantee number is positive`() {
        positive.should().bePositive()
    }

    @ParameterizedTest
    @ValueSource(doubles = [positive, zero])
    fun `should fail when number is not negatives`(notNegative: Double) {
        runCatching {
            notNegative.should().beNegative()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$notNegative should be < 0")
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
    fun `should guarantee than number is less or equal than`() {
        less.should() beLessOrEqualThan less beLessOrEqualThan more
    }

    @Test
    fun `should fail when number is not less than byte`() {
        runCatching {
            more.should() beLessThan less.toByte()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$more should be < ${less.toByte()}")
    }

    @Test
    fun `should guarantee than number is less than byte`() {
        less.should() beLessThan more.toByte()
    }

    @Test
    fun `should fail when number is not less than short`() {
        runCatching {
            more.should() beLessThan less.toShort()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$more should be < ${less.toShort()}")
    }

    @Test
    fun `should guarantee than number is less than short`() {
        less.should() beLessThan more.toShort()
    }

    @Test
    fun `should fail when number is not less than int`() {
        runCatching {
            more.should() beLessThan less.toInt()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$more should be < ${less.toInt()}")
    }

    @Test
    fun `should guarantee than number is less than int`() {
        less.should() beLessThan more.toInt()
    }

    @Test
    fun `should fail when number is not less than long`() {
        runCatching {
            more.should() beLessThan less.toLong()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$more should be < ${less.toLong()}")
    }

    @Test
    fun `should guarantee than number is less than long`() {
        less.should() beLessThan more.toLong()
    }

    @Test
    fun `should fail when number is not less than float`() {
        runCatching {
            more.should() beLessThan less.toFloat()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$more should be < ${less.toFloat()}")
    }

    @Test
    fun `should guarantee than number is less than float`() {
        less.should() beLessThan more.toFloat()
    }

    @Test
    fun `should fail when number is not greater than`() {
        runCatching {
            less.should() beGreaterThan more
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$less should be > $more")
    }

    @Test
    fun `should guarantee than number is greater than`() {
        more.should() beGreaterThan less
    }

    @Test
    fun `should fail when number is not greater than byte`() {
        runCatching {
            less.should() beGreaterThan more.toByte()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$less should be > ${more.toByte()}")
    }

    @Test
    fun `should guarantee than number is greater than byte`() {
        more.should() beGreaterThan less.toByte()
    }

    @Test
    fun `should fail when number is not greater than short`() {
        runCatching {
            less.should() beGreaterThan more.toShort()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$less should be > ${more.toShort()}")
    }

    @Test
    fun `should guarantee than number is greater than short`() {
        more.should() beGreaterThan less.toShort()
    }

    @Test
    fun `should fail when number is not greater than int`() {
        runCatching {
            less.should() beGreaterThan more.toInt()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$less should be > ${more.toInt()}")
    }

    @Test
    fun `should guarantee than number is greater than int`() {
        more.should() beGreaterThan less.toInt()
    }

    @Test
    fun `should fail when number is not greater than long`() {
        runCatching {
            less.should() beGreaterThan more.toLong()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$less should be > ${more.toLong()}")
    }

    @Test
    fun `should guarantee than number is greater than long`() {
        more.should() beGreaterThan less.toLong()
    }

    @Test
    fun `should fail when number is not greater than float`() {
        runCatching {
            less.should() beGreaterThan more.toFloat()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$less should be > ${more.toFloat()}")
    }

    @Test
    fun `should guarantee than number is greater than float`() {
        more.should() beGreaterThan less.toFloat()
    }

    @ParameterizedTest
    @MethodSource(value = ["notBetween"])
    fun `should fail when number is not between`(number: Double, lower: Double, higher: Double) {
        runCatching {
            number.should().beBetween(lower, higher)
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$number should be between ($lower .. $higher)")
    }

    @Test
    fun `should guarantee than number is between`() {
        zero.should().beBetween(negative, positive)
    }

    companion object {
        private const val zero = 0.0
        private const val negative = -20.0
        private const val positive = 20.0

        @JvmStatic
        fun notBetween(): Stream<Arguments> = Stream.of(
                Arguments.of(negative, zero, positive),
                Arguments.of(zero, zero, positive),
                Arguments.of(positive, negative, positive)
        )
    }
}
