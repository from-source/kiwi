package io.fromsource.kiwi.core.collection

import io.fromsource.kiwi.core.should
import kotlin.test.Test

class ListShouldTest {

    @Test
    fun `should fail when lists are not equaled`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should beEqual listOf(1, 2, 1)
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should == [1, 2, 1]")
    }

    @Test
    fun `should guarantee list are equaled`() {
        val numbers = listOf(1, 2, 3)

        numbers.should beEqual listOf(1, 2, 3)
    }

    @Test
    fun `should fail when list does not have expected size`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should haveSize numbers.size - 1
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("Expected size: 2, actual: 3")
    }

    @Test
    fun `should guarantee list have size`() {
        val numbers = listOf(1, 2, 3)

        numbers.should haveSize 3
    }

    @Test
    fun `should fail when list does not contain element`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should contain 4
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should contain 4")
    }

    @Test
    fun `should guarantee that list contains element`() {
        val numbers = listOf(1, 2, 3)

        numbers.should contain 3
    }

    @Test
    fun `should fail when list is not empty`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should.beEmpty()
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should be empty")
    }

    @Test
    fun `should guarantee that list is empty`() {
        val numbers = emptyList<Number>()

        numbers.should.beEmpty()
    }

    @Test
    fun `should fail when list does not contain all elements in order`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should.contain(3, 4)
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should contain 4")
    }

    @Test
    fun `should guarantee that list contains all elements`() {
        val numbers = listOf(1, 2, 3)

        numbers.should.contain(1, 3)
    }

    @Test
    fun `should fail when non empty list does not have 1st element as`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should have1st 2
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should have first element to be 2")
    }

    @Test
    fun `should guarantee that list contains have element to be equal at first position`() {
        val numbers = listOf(1, 2, 3)

        numbers.should have1st 1
    }

    @Test
    fun `should fail when empty list expected to have element at first position`() {
        val numbers = emptyList<Number>()

        runCatching {
            numbers.should have1st 2
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("[] should have first element to be 2")
    }

    @Test
    fun `should fail when non empty list does not have 2nd element as`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should have2nd 1
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should have second element to be 1")
    }

    @Test
    fun `should guarantee that list have element to be equal at second position`() {
        val numbers = listOf(1, 2, 3)

        numbers.should have2nd 2
    }

    @Test
    fun `should fail when empty list expected to have element at second position`() {
        val numbers = emptyList<Number>()

        runCatching {
            numbers.should have2nd 2
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("[] should have second element to be 2")
    }

    @Test
    fun `should fail when list does not match any predicate`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should matchAny { it == 4 }
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should match any predicate")
    }

    @Test
    fun `should guarantee that list match any predicate`() {
        val numbers = listOf(1, 2, 3)

        numbers.should matchAny { it == 2 }
    }

    @Test
    fun `should fail when list does not match all predicate`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should matchAll { it > 2 }
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should match all predicate")
    }

    @Test
    fun `should pass when all elements pass predicate`() {
        val numbers = listOf(1, 2, 3)

        numbers.should matchAll { it > 0 }
    }

    @Test
    fun `should guarantee list match all predicate`() {
        val numbers = listOf(1, 2, 3)

        numbers.should matchAny { it < 4 }
    }

    @Test
    fun `should fail when list does not equal other list`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should beEqual listOf(1, 2, 4)
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("[1, 2, 3] should == [1, 2, 4]")
    }

    @Test
    fun `should guarantee list is equal other list`() {
        val numbers = listOf(1, 2, 3)

        numbers.should beEqual listOf(1, 2, 3)
    }

    @Test
    fun `should fail when extracting 1st element`() {
        val numbers = emptyList<Number>()

        runCatching {
            numbers.should.first()
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("[] should have first element")
    }

    @Test
    fun `should extract first element`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should.first()
        }.should
                .beSuccess(1)
    }

    @Test
    fun `should fail when extracting last element`() {
        val numbers = emptyList<Number>()

        runCatching {
            numbers.should.last()
        }.should
                .beFailure(AssertionError::class)
                .haveFailureMessage("[] should have last element")
    }

    @Test
    fun `should extract last element`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should.last()
        }.should
                .beSuccess(3)
    }

    @Test
    fun `should fail when list is not sorted by provided selector`() {
        val words = listOf("kiwi", "anaconda", "crocodile", "emu")

        runCatching {
            words.should.beSorted { it.length }
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$words should be sorted: [emu, kiwi, anaconda, crocodile]")
    }

    @Test
    fun `should guarantee list is sorted by provided selector`() {
        val words = listOf("emu", "kiwi", "anaconda", "crocodile")

        words.should.beSorted { it.length }
    }

    @Test
    fun `should fail when list is not sorted by its natural order`() {
        val words = listOf("anaconda", "crocodile", "kiwi", "emu")

        runCatching {
            words.should.beSorted()
        }.should
            .beFailure(AssertionError::class)
            .haveFailureMessage("$words should be sorted: [anaconda, crocodile, emu, kiwi]")
    }

    @Test
    fun `should guarantee list is sorted by its natural order`() {
        val words = listOf("anaconda", "crocodile", "emu", "kiwi")

        words.should.beSorted()
    }
}


