package io.fromsource.kiwi.core.collection

import io.fromsource.kiwi.core.should
import kotlin.test.Test

class MapShouldTest {

    @Test
    fun `should fail when map is not empty`() {
        val letters = mapOf("a" to 1, "b" to 2)

        runCatching {
            letters.should().beEmpty()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("{a=1, b=2} should be empty")
    }

    @Test
    fun `should guarantee map is empty`() {
        val letters = emptyMap<String, Int>()

        letters.should().beEmpty()
    }

    @Test
    fun `should fail when map has not proper size`() {
        val letters = mapOf("a" to 1, "b" to 2)

        runCatching {
            letters.should() haveSize 3
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("{a=1, b=2} should have size 3")
    }

    @Test
    fun `should guarantee map has proper size`() {
        val letters = mapOf("a" to 1, "b" to 2)

        letters.should() haveSize 2
    }

    @Test
    fun `should fail when maps are not equaled`() {
        val letters = mapOf("a" to 1, "b" to 2)

        runCatching {
            letters.should() beEqual mapOf("a" to 1, "c" to 3)
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("{a=1, b=2} should == {a=1, c=3}")
    }

    @Test
    fun `should guarantee maps are equaled`() {
        val letters = mapOf("a" to 1, "b" to 2)

        letters.should() beEqual mapOf("b" to 2, "a" to 1)
    }

    @Test
    fun `should fail when map does not contain key`() {
        val letters = mapOf("a" to 1, "b" to 2)

        runCatching {
            letters.should() containKey "c"
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("{a=1, b=2} should contain key 'c'")
    }

    @Test
    fun `should guarantee map contain key`() {
        val letters = mapOf("a" to 1, "b" to 2)

        letters.should() containKey "a"
    }

    @Test
    fun `should fail when map does not contain value`() {
        val letters = mapOf("a" to 1, "b" to 2)

        runCatching {
            letters.should() containValue 0
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("{a=1, b=2} should contain value '0'")
    }

    @Test
    fun `should guarantee map contain value`() {
        val letters = mapOf("a" to 1, "b" to 2)

        letters.should() containValue 2
    }

    @Test
    fun `should fail when map does not match any predicate`() {
        val letters = mapOf("a" to 1, "b" to 2)

        runCatching {
            letters.should() matchAny { (key, _) -> key == "c" }
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("{a=1, b=2} should match any predicate")
    }

    @Test
    fun `should guarantee map match any predicate`() {
        val letters = mapOf("a" to 1, "b" to 2)

        letters.should() matchAny { (key, _) -> key == "a" }
    }

    @Test
    fun `should fail when map does not match all predicate`() {
        val letters = mapOf("a" to 1, "b" to 2)

        runCatching {
            letters.should() matchAll { (key, _) -> key == "c" }
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("{a=1, b=2} should match all predicate")
    }

    @Test
    fun `should guarantee map match all predicate`() {
        val letters = mapOf("a" to 1, "b" to 2)

        letters.should() matchAll { (_, value) -> value > 0 }
    }
}