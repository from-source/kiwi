package io.github.fromsource.kiwi.core.number

import io.github.fromsource.kiwi.core.should
import org.junit.jupiter.api.Test

class FloatShouldTest {
    private val more = 10.0f
    private val less = 1.0f

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
    fun `should fail when number is not less than double`() {
        runCatching {
            more.should() beLessThan less.toDouble()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$more should be < ${less.toDouble()}")
    }

    @Test
    fun `should guarantee than number is less than double`() {
        less.should() beLessThan more.toDouble()
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
}

