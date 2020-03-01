package org.fromsource.kiwi.core.bool

import org.fromsource.kiwi.core.should
import kotlin.test.Test

internal class BooleanShouldTest {

    @Test
    fun `should guarantee true is equal to true`() {
        true.should.beEqual(true)
    }

    @Test
    fun `should guarantee false is equal to false`() {
        false.should.beEqual(false)
    }

    @Test
    fun `should fail when true is not equal to false`() {
        runCatching {
            true.should.beEqual(false)
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("true should == false")
    }

    @Test
    fun `should fail when false is not equal to true`() {
        runCatching {
            false.should.beEqual(true)
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("false should == true")
    }

    @Test
    fun `should guarantee true is true`() {
        true.should.beTrue()
    }

    @Test
    fun `should guarantee false is false`() {
        false.should.beFalse()
    }

    @Test
    fun `should fail when false is not true`() {
        runCatching {
            false.should.beTrue()
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("false should == true")
    }

    @Test
    fun `should guarantee true should be true`() {
        true.should be true
    }
}