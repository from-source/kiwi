package io.fromsource.kiwi.core.number

import io.fromsource.kiwi.core.should
import kotlin.test.Test

class ByteShouldTest {

    @Test
    fun `should fail when numbers are not equaled`() {
        runCatching {
            more.should beEqual less
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$more should == $less")
    }

    @Test
    fun `should guarantee numbers are equaled`() {
        more.should beEqual more
    }

    @Test
    fun `should fail because negative is not positive`() {
        runCatching {
            negative.should.bePositive()
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("${negative} should be > 0")
    }

    @Test
    fun `should fail because zero is not positive`() {
        runCatching {
            zero.should.bePositive()
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("${zero} should be > 0")
    }

    @Test
    fun `should guarantee number is positive`() {
        positive.should.bePositive()
    }

    @Test
    fun `should fail because positive number is not negatives`() {
        runCatching {
            positive.should.beNegative()
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$positive should be < 0")
    }

    @Test
    fun `should fail because zero number is not negatives`() {
        runCatching {
            zero.should.beNegative()
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$zero should be < 0")
    }

    @Test
    fun `should guarantee number is negative`() {
        negative.should.beNegative()
    }

    @Test
    fun `should fail when number is not less than`() {
        runCatching {
            more.should beLessThan less
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$more should be < $less")
    }

    @Test
    fun `should guarantee than number is less than`() {
        less.should beLessThan more
    }

    @Test
    fun `should fail when number is not less or equal than`() {
        runCatching {
            more.should beLessOrEqualThan less
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$more should be <= $less")
    }

    @Test
    fun `should guarantee than number is less or equal than`() {
        less.should beLessOrEqualThan less beLessOrEqualThan more
    }

    @Test
    fun `should fail when number is not less than short`() {
        runCatching {
            more.should beLessThan less.toShort()
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$more should be < ${less.toShort()}")
    }

    @Test
    fun `should guarantee than number is less than short`() {
        less.should beLessThan more.toShort()
    }

    @Test
    fun `should fail when number is not less than ints`() {
        runCatching {
            more.should beLessThan less.toInt()
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$more should be < ${less.toInt()}")
    }

    @Test
    fun `should guarantee than number is less than ints`() {
        less.should beLessThan more.toInt()
    }

    @Test
    fun `should fail when number is not less than long`() {
        runCatching {
            more.should beLessThan less.toLong()
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$more should be < ${less.toLong()}")
    }

    @Test
    fun `should guarantee than number is less than long`() {
        less.should beLessThan more.toLong()
    }

    @Test
    fun `should fail when number is not less than float`() {
        runCatching {
            more.should beLessThan less.toFloat()
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$more should be < ${less.toFloat()}")
    }

    @Test
    fun `should guarantee than number is less than float`() {
        less.should beLessThan more.toFloat()
    }

    @Test
    fun `should fail when number is not less than double`() {
        runCatching {
            more.should beLessThan less.toDouble()
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$more should be < ${less.toDouble()}")
    }

    @Test
    fun `should guarantee than number is less than double`() {
        less.should beLessThan more.toDouble()
    }

    @Test
    fun `should fail when number is not greater than`() {
        runCatching {
            less.should beGreaterThan more
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$less should be > $more")
    }

    @Test
    fun `should guarantee than number is greater than`() {
        more.should beGreaterThan less
    }


    @Test
    fun `should fail when number is not greater than short`() {
        runCatching {
            less.should beGreaterThan more.toShort()
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$less should be > ${more.toShort()}")
    }

    @Test
    fun `should guarantee than number is greater than short`() {
        more.should beGreaterThan less.toShort()
    }

    @Test
    fun `should fail when number is not greater than int`() {
        runCatching {
            less.should beGreaterThan more.toInt()
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$less should be > ${more.toInt()}")
    }

    @Test
    fun `should guarantee than number is greater than int`() {
        more.should beGreaterThan less.toInt()
    }

    @Test
    fun `should fail when number is not greater than long`() {
        runCatching {
            less.should beGreaterThan more.toLong()
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$less should be > ${more.toLong()}")
    }

    @Test
    fun `should guarantee than number is greater than long`() {
        more.should beGreaterThan less.toLong()
    }

    @Test
    fun `should fail when number is not greater than float`() {
        runCatching {
            less.should beGreaterThan more.toFloat()
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$less should be > ${more.toFloat()}")
    }

    @Test
    fun `should guarantee than number is greater than float`() {
        more.should beGreaterThan less.toFloat()
    }

    @Test
    fun `should fail when number is not greater than double`() {
        runCatching {
            less.should beGreaterThan more.toDouble()
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$less should be > ${more.toDouble()}")
    }

    @Test
    fun `should guarantee than number is greater than double`() {
        more.should beGreaterThan less.toDouble()
    }

    @Test
    fun `should fail because number is not between (0, positive)`() {
        runCatching {
            negative.should.beBetween(zero, positive)
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$negative should be between ($zero .. $positive)")
    }

    @Test
    fun `should fail when because 0 is not between (0, positive)`() {
        runCatching {
            zero.should.beBetween(zero, positive)
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$zero should be between ($zero .. $positive)")
    }

    @Test
    fun `should fail when because positive number is not between (netagive, positive)`() {
        runCatching {
            positive.should.beBetween(zero, positive)
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$positive should be between ($zero .. $positive)")
    }

    @Test
    fun `should guarantee than number is between`() {
        zero.should.beBetween(negative, positive)
    }

    @Test
    fun `should fail when because number is not zero`() {
        runCatching {
            positive.should.beZero()
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$positive should == $zero")
    }

    @Test
    fun `should guarantee number is zero`() {
        zero.should.beZero()
    }

    companion object {
        private const val more: Byte = 10
        private const val less: Byte = 1

        private const val zero: Byte = 0
        private const val negative: Byte = -20
        private const val positive: Byte = 20
    }
}

