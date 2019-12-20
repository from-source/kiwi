package io.fromsource.kiwi.core

import kotlin.test.Test

class PairShouldTest {

    private val pair = Pair("one", 1)

    @Test
    fun `should guarantee first element of pair equals to`() {
        pair.should().firstBeEqual("one")
    }

    @Test
    fun `should fail because first element of pair does not equal expected`() {
        runCatching {
            pair.should().firstBeEqual("two")
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$pair should have first element equal to two")
    }

    @Test
    fun `should guarantee second element of pair equals to`() {
        Pair("one", 1).should().secondBeEqual(1)
    }

    @Test
    fun `should fail because second element of pair does not equal expected`() {
        runCatching {
            pair.should().secondBeEqual(2)
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$pair should have second element equal to 2")

    }
}