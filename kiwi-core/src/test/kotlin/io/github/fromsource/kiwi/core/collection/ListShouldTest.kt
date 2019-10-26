package io.github.fromsource.kiwi.core.collection

import io.github.fromsource.kiwi.core.should
import io.github.fromsource.kiwi.core.util.runCatching
import org.junit.jupiter.api.Test

class ListShouldTest {

    @Test
    fun `should fail when list does not have expected size`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should() haveSize numbers.size - 1
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("Expected size: 2, actual: 3")
    }

    @Test
    fun `should guarantee list have size`() {
        val numbers = listOf(1, 2, 3)

        numbers.should() haveSize 3
    }

    @Test
    fun `should fail when list does not contain element`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should() contain 4
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should contain 4")
    }

    @Test
    fun `should guarantee that list contains element`() {
        val numbers = listOf(1, 2, 3)

        numbers.should() contain 3
    }

    @Test
    fun `should fail when list is not empty`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should().beEmpty()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should be empty")
    }

    @Test
    fun `should guarantee that list is empty`() {
        val numbers = emptyList<Number>()

        numbers.should().beEmpty()
    }

    @Test
    fun `should fail when list does not contain all elements in order`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should().contain(3, 4)
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should contain 4")
    }

    @Test
    fun `should guarantee that list contains all elements`() {
        val numbers = listOf(1, 2, 3)

        numbers.should().contain(1, 3)
    }

    @Test
    fun `should fail when non empty list does not have 1st element as`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should() have1st 2
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should have first element to be 2")
    }

    @Test
    fun `should guarantee that list contains have element to be equal at first position`() {
        val numbers = listOf(1, 2, 3)

        numbers.should() have1st 1
    }

    @Test
    fun `should fail when empty list expected to have element at first position`() {
        val numbers = emptyList<Number>()

        runCatching {
            numbers.should() have1st 2
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("[] should have first element to be 2")
    }

    @Test
    fun `should fail when non empty list does not have 2nd element as`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should() have2nd 1
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should have second element to be 1")
    }

    @Test
    fun `should guarantee that list contains have element to be equal at second position`() {
        val numbers = listOf(1, 2, 3)

        numbers.should() have2nd 2
    }

    @Test
    fun `should fail when empty list expected to have element at second position`() {
        val numbers = emptyList<Number>()

        runCatching {
            numbers.should() have2nd 2
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("[] should have second element to be 2")
    }

    @Test
    fun `should fail when list does not match any predicate`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should() matchAny { it == 4 }
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should match any predicate")
    }

    @Test
    fun `should list match any predicate`() {
        val numbers = listOf(1, 2, 3)

        numbers.should() matchAny { it == 2 }
    }

    @Test
    fun `should fail when list does not match all predicate`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should() matchAll { it == 4 }
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should match all predicate")
    }

    @Test
    fun `should list match all predicate`() {
        val numbers = listOf(1, 2, 3)

        numbers.should() matchAny { it < 4 }
    }
}



