package org.fromsource.kiwi.core

import kotlin.test.Test

class Anything {
    override fun toString(): String = "anything"
}

class Something {
    override fun toString(): String = "something"
}

class AnyShouldTest {
    private val trueCondition: (Any) -> Boolean = { true }
    private val falseCondition: (Any) -> Boolean = { false }

    private val anything = Anything()
    private val something = Something()

    @Test
    fun `should fail because condition does not match`() {
        runCatching {
            anything.should match falseCondition
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("$anything should match condition $falseCondition")
    }

    @Test
    fun `should guarantee anything match condition`() {
        anything.should match trueCondition
    }

    @Test
    fun `should fail because condition match, but should not`() {
        runCatching {
            anything.should notMatch trueCondition
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("$anything should not match condition $trueCondition")
    }

    @Test
    fun `should guarantee anything does not match condition`() {
        anything.should notMatch falseCondition
    }

    @Test
    fun `should fail because objects are not equal`() {
        runCatching {
            anything.toString().should beEqual something.toString()
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("$anything should == $something")
    }

    @Test
    fun `should guarantee objects equal`() {
        anything.toString().should beEqual "anything"
    }

    @Test
    fun `should fail because objects are equal`() {
        runCatching {
            anything.toString().should notBeEqual anything.toString()
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("$anything should != $anything")
    }

    @Test
    fun `should guarantee objects not equal`() {
        anything.should notBeEqual "anything".toUpperCase()
    }

    @Test
    fun `should fail because objects are not the same`() {
        runCatching {
            anything.should beTheSame Anything()
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("$anything should be the same as $anything")
    }

    @Test
    fun `should guarantee objects are the same`() {
        anything.should beTheSame anything
    }

    @Test
    fun `should fail because objects are the same`() {
        runCatching {
            anything.should notBeTheSame anything
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("$anything should not be the same as $anything")
    }

    @Test
    fun `should guarantee objects are not the same`() {
        anything.should notBeTheSame Anything()
    }

    @Test
    fun `should fail because object does not have expected hashCode`() {
        val differentHashCode = -1 * anything.hashCode()
        runCatching {
            anything.should haveHashCodeEqualTo differentHashCode
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("$anything should have hashCode equal $differentHashCode, but has ${anything.hashCode()}")
    }

    @Test
    fun `should guarantee object has expected hashCode`() {
        anything.should haveHashCodeEqualTo anything.hashCode()
    }

    @Test
    fun `should fail because object has expected hashCode`() {
        val differentHasCode = anything.hashCode()
        runCatching {
            anything.should notHaveHashCodeEqualTo differentHasCode
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("$anything should not have hashCode equal $differentHasCode, but has ${anything.hashCode()}")
    }

    @Test
    fun `should guarantee object has not expected hashCode`() {
        anything.should notHaveHashCodeEqualTo anything.hashCode() * -1
    }
}
