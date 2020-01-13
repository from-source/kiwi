package io.fromsource.kiwi.core.number

import io.fromsource.kiwi.core.should
import io.fromsource.kiwi.core._should
import kotlin.test.Test

class ULongShouldTest {

    @Test
    fun `should fail because numbers are not equaled`() {
        runCatching {
            more._should() beEqual less
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$more should == $less")
    }

    @Test
    fun `should guarantee numbers are equaled`() {
        more._should() beEqual more
    }

    @Test
    fun `should fail because number is not zero`() {
        runCatching {
            more._should().beZero()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$more should == $zero")
    }

    @Test
    fun `should guarantee that number is zero`() {
        zero._should().beZero()
    }

    @Test
    fun `should fail because number is not less or equal`() {
        runCatching {
            more._should() beLessOrEqualThan less
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$more should be <= $less")
    }

    @Test
    fun `should guarantee than number is less or equal`() {
        less._should() beLessOrEqualThan less
    }

    @Test
    fun `should fail because number is not greater or equal`() {
        runCatching {
            less._should() beGreaterOrEqualThan more
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$less should be >= $more")
    }

    @Test
    fun `should guarantee than number is greater or equal`() {
        less._should() beGreaterOrEqualThan less
    }

    @Test
    fun `should fail because number is not less`() {
        runCatching {
            more._should() beLessThan less
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$more should be < $less")
    }

    @Test
    fun `should guarantee than number is less`() {
        less._should() beLessThan more
    }

    @Test
    fun `should fail because number is not less than ubyte`() {
        runCatching {
            more._should() beLessThan less.toUByte()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$more should be < ${less.toUByte()}")
    }

    @Test
    fun `should guarantee than number is less than ubyte`() {
        less._should() beLessThan more.toUByte()
    }

    @Test
    fun `should fail because number is not less than ushort`() {
        runCatching {
            more._should() beLessThan less.toUShort()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$more should be < ${less.toUShort()}")
    }

    @Test
    fun `should guarantee than number is less than ushort`() {
        less._should() beLessThan more.toUShort()
    }

    @Test
    fun `should fail because number is not less than uint`() {
        runCatching {
            more._should() beLessThan less.toUInt()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$more should be < ${less.toUInt()}")
    }

    @Test
    fun `should guarantee than number is less than uint`() {
        less._should() beLessThan more.toUInt()
    }

    @Test
    fun `should fail because number is not greater than ubyte`() {
        runCatching {
            less._should() beGreaterThan more.toUByte()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$less should be > $more")
    }

    @Test
    fun `should guarantee than number is greater than ubyte`() {
        more._should() beGreaterThan less.toUByte()
    }

    @Test
    fun `should fail because number is not greater than ushort`() {
        runCatching {
            less._should() beGreaterThan more.toUShort()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$less should be > ${more.toUShort()}")
    }

    @Test
    fun `should guarantee than number is greater than ushort`() {
        more._should() beGreaterThan less.toUShort()
    }

    @Test
    fun `should fail because number is not greater`() {
        runCatching {
            less._should() beGreaterThan more
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$less should be > $more")
    }

    @Test
    fun `should guarantee than number is greater`() {
        more._should() beGreaterThan less
    }

    @Test
    fun `should fail because number is not greater than uint`() {
        runCatching {
            less._should() beGreaterThan more.toUInt()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$less should be > ${more.toUInt()}")
    }

    @Test
    fun `should guarantee than number is greater than uint`() {
        more._should() beGreaterThan less.toUInt()
    }

    @Test
    fun `should fail because number is not between`() {
        runCatching {
            more._should().beBetween(zero, less)
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$more should be between ($zero .. $less)")
    }

    @Test
    fun `should guarantee number is between`() {
        less._should().beBetween(zero, more)
    }

    companion object {
        val zero = ULong.MIN_VALUE
        val less = 1.toULong()
        val more = 10.toULong()
    }
}