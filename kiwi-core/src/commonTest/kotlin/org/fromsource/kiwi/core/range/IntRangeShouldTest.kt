package org.fromsource.kiwi.core.range

import org.fromsource.kiwi.core.should
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

    @Test
    fun `should fail when range does not start with`() {
        runCatching {
            (1..10).should startWith 2
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("1..10 should start with 2")
    }

    @Test
    fun `should guarantee range starts with`() {
        (1..10).should startWith 1
    }

    @Test
    fun `should fail when range starts with`() {
        runCatching {
            (1..10).should notStartWith 1
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("1..10 should not start with 1")
    }

    @Test
    fun `should guarantee range does not starts with`() {
        (1..10).should notStartWith 2
    }

    @Test
    fun `should fail when range does not end with`() {
        runCatching {
            (1..10).should endWith 9
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("1..10 should end with 9")
    }

    @Test
    fun `should guarantee range ends with`() {
        (1..10).should endWith 10
    }

    @Test
    fun `should fail when range end with`() {
        runCatching {
            (1..10).should notEndWith 10
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("1..10 should not end with 10")
    }

    @Test
    fun `should guarantee range does not end with`() {
        (1..10).should notEndWith 9
    }

    @Test
    fun `should fail when range does not contain`() {
        runCatching {
            (1..10).should contain 11
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("1..10 should contain 11")
    }

    @Test
    fun `should guarantee range contains`() {
        (1..10).should contain 8
    }

    @Test
    fun `should fail when range contains`() {
        runCatching {
            (1..10).should notContain 8
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("1..10 should not contain 8")
    }

    @Test
    fun `should guarantee range does not contain`() {
        (1..10).should notContain 11
    }

    @Test
    fun `should fail when range is empty`() {
        runCatching {
            (1..10).should.beEmpty()
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("1..10 should be empty")
    }

    @Test
    fun `should guarantee range is empty`() {
        IntRange.EMPTY.should.beEmpty()
    }

    @Test
    fun `should fail when range is not empty`() {
        runCatching {
            IntRange.EMPTY.should.notBeEmpty()
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("1..0 should not be empty")
    }

    @Test
    fun `should guarantee range is not empty`() {
        (1..10).should.notBeEmpty()
    }
}
