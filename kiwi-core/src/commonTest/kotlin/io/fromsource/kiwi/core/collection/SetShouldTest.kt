package io.fromsource.kiwi.core.collection

import io.fromsource.kiwi.core.should
import kotlin.test.Test

class SetShouldTest {

    @Test
    fun `should fail when sets are not equaled`() {
        val numbers = setOf(1, 2, 3)

        runCatching {
            numbers.should() beEqual setOf(1, 2, 1)
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should == [1, 2]")
    }

    @Test
    fun `should guarantee sets are equaled`() {
        val numbers = setOf(1, 2, 3)

        numbers.should() beEqual setOf(1, 2, 3)
    }

    @Test
    fun `should fail when set does not have expected size`() {
        val numbers = setOf(1, 2, 3)

        runCatching {
            numbers.should() haveSize numbers.size - 1
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("Expected size: 2, actual: 3")
    }

    @Test
    fun `should guarantee set have size`() {
        val numbers = setOf(1, 2, 3)

        numbers.should() haveSize 3
    }

    @Test
    fun `should fail when set does not contain element`() {
        val numbers = setOf(1, 2, 3)

        runCatching {
            numbers.should() contain 4
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should contain 4")
    }

    @Test
    fun `should guarantee that set contains element`() {
        val numbers = setOf(1, 2, 3)

        numbers.should() contain 3
    }

    @Test
    fun `should fail when set is not empty`() {
        val numbers = setOf(1, 2, 3)

        runCatching {
            numbers.should().beEmpty()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should be empty")
    }

    @Test
    fun `should guarantee that set is empty`() {
        val numbers = emptyList<Number>()

        numbers.should().beEmpty()
    }

    @Test
    fun `should fail when set does not contain all elements`() {
        val numbers = setOf(1, 2, 3)

        runCatching {
            numbers.should().contain(3, 4)
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should contain 4")
    }

    @Test
    fun `should guarantee that set contains all elements`() {
        val numbers = setOf(1, 2, 3)

        numbers.should().contain(1, 3)
    }

    @Test
    fun `should fail when set does not match any predicate`() {
        val numbers = setOf(1, 2, 3)

        runCatching {
            numbers.should() matchAny { it == 4 }
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should match any predicate")
    }

    @Test
    fun `should guarantee that list match any predicate`() {
        val numbers = setOf(1, 2, 3)

        numbers.should() matchAny { it == 2 }
    }

    @Test
    fun `should fail when set does not match all predicate`() {
        val numbers = setOf(1, 2, 3)

        runCatching {
            numbers.should() matchAll { it == 4 }
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should match all predicate")
    }

    @Test
    fun `should guarantee set match all predicate`() {
        val numbers = setOf(1, 2, 3)

        numbers.should() matchAny { it < 4 }
    }

    @Test
    fun `should fail when set does not equal other set`() {
        val numbers = setOf(1, 2, 3)

        runCatching {
            numbers.should() beEqual setOf(1, 2, 4)
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should == [1, 2, 4]")
    }

    @Test
    fun `should guarantee set is equal other set`() {
        val numbers = setOf(1, 2, 3)

        numbers.should() beEqual setOf(1, 2, 3)
    }

}


