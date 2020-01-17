package io.fromsource.kiwi.core.range

import io.fromsource.kiwi.core.should
import kotlin.test.Test

class IntRangeShouldTest {

    @Test
    fun `should fail when ranges are not equaled`() {
        runCatching {
            (1..10).should beEqual (1..9)
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("1..10 should == 1..9")
    }

    @Test
    fun `should guarantee ranges are equaled`() {
        (1..10).should beEqual (1..10)
    }

    @Test
    fun `should fail when ranges are equaled`() {
        runCatching {
            (1..10).should notBeEqual (1..10)
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("1..10 should != 1..10")
    }

    @Test
    fun `should guarantee ranges are not equaled`() {
        (1..10).should notBeEqual (1..9)
    }

    @Test
    fun `should fail when range does not match condition`() {
        runCatching {
            (1..10).should match { it.isEmpty() }
        }.should
                .beFailure(AssertionError::class)
    }

    @Test
    fun `should guarantee range matches condition`() {
        (1..10).should match { !it.isEmpty() }
    }

    @Test
    fun `should fail when range matches condition`() {
        runCatching {
            (1..10).should notMatch { it.first == 1 }
        }.should
                .beFailure(AssertionError::class)
    }

    @Test
    fun `should guarantee range does not match condition`() {
        (1..10).should notMatch { it.first == 10 }
    }
}
