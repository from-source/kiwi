package org.fromsource.kiwi.core

import kotlin.test.Test

class TripleShouldTest {

    private val triple = Triple("one", 1, 1.0)

    @Test
    fun `should guarantee first element of triple equals to`() {
        triple.should.haveFirstEqualTo("one")
    }

    @Test
    fun `should fail because first element of triple does not equal expected`() {
        runCatching {
            triple.should.haveFirstEqualTo("two")
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("$triple should have first element equal to two")
    }

    @Test
    fun `should guarantee second element of triple equals to`() {
        triple.should.haveSecondEqualTo(1)
    }

    @Test
    fun `should fail because second element of triple does not equal expected`() {
        runCatching {
            triple.should.haveSecondEqualTo(2)
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("$triple should have second element equal to 2")
    }

    @Test
    fun `should guarantee third element of triple equals to`() {
        triple.should.haveThirdEqualTo(1.0)
    }

    @Test
    fun `should fail because third element of triple does not equal expected`() {
        runCatching {
            triple.should.haveThirdEqualTo(2.0)
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("$triple should have third element equal to 2.0")
    }

    @Test
    fun `should combine assertions`() {
        triple.should
                .haveFirstEqualTo("one")
                .haveSecondEqualTo(1)
                .haveThirdEqualTo(1.0)
    }
}
