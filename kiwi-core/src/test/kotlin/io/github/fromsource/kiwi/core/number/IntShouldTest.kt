package io.github.fromsource.kiwi.core.number

import io.github.fromsource.kiwi.core.should
import io.github.fromsource.kiwi.core.util.runCatching
import org.junit.jupiter.api.Test

class IntShouldTest {

    @Test
    fun `should fail when numbers are not equaled`() {
        runCatching {
            10.should() beEqual 9
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("10 should be == 9")
    }

    @Test
    fun `should guarantee numbers are equaled`() {
        10.should() beEqual (10)
    }

    @Test
    fun `should fail when number is not less than`() {
        runCatching {
            10.should() beLessThan 1
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("10 should be < 1")
    }

    @Test
    fun `should guarantee than number is less than`() {
        10.should() beLessThan 11
    }

    @Test
    fun `should fail when number is not greater than`() {
        runCatching {
            1.should() beGreaterThan 2
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("1 should be > 2")
    }

    @Test
    fun `should guarantee than number is greater than`() {
        11.should() beGreaterThan 10
    }
}

