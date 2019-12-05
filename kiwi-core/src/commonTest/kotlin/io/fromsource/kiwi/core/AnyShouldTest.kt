package io.fromsource.kiwi.core

import kotlin.test.Test

class Anything {
    override fun toString(): String = "anything"
}

class AnyShouldTest {
    private val trueCondition: (Any) -> Boolean = { true }
    private val falseCondition: (Any) -> Boolean = { false }

    private val anything = Anything()

    @Test
    fun `should fail because condition does not match`() {
        runCatching {
            anything.should() match falseCondition
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$anything should match condition $falseCondition")
    }

    @Test
    fun `should guarantee anything match condition`() {
        anything.should() match trueCondition
    }

    @Test
    fun `should fail because condition match, but should not`() {
        runCatching {
            anything.should() notMatch trueCondition
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("$anything should not match condition $trueCondition")
    }

    @Test
    fun `should guarantee anything does not match condition`() {
        anything.should() notMatch falseCondition
    }
}
