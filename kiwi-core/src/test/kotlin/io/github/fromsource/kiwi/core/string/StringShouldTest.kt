package io.github.fromsource.kiwi.core.string

import io.github.fromsource.kiwi.core.should
import io.github.fromsource.kiwi.core.util.runCatching
import org.junit.jupiter.api.Test

class StringShouldTest {

    @Test
    fun `should fail when string is not empty`() {
        runCatching {
            "hello kiwi".should().beEmpty()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'hello kiwi' should be empty")
    }

    @Test
    fun `should guarantee string is empty`() {
        "".should().beEmpty()
    }

    @Test
    fun `should fail when string is empty`() {
        runCatching {
            "".should().notBeEmpty()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'' should not be empty")
    }

    @Test
    fun `should guarantee string is not empty`() {
        "kiwi!".should().notBeEmpty()
    }

    @Test
    fun `should fail when string is not blank`() {
        runCatching {
            "hello kiwi".should().beBlank()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'hello kiwi' should be blank")
    }

    @Test
    fun `should guarantee string is blank`() {
        " ".should().beBlank()
    }

    @Test
    fun `should fail when strings are not equal`() {
        runCatching {
            "hello kiwi".should() beEqual "hello kiwi!"
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'hello kiwi' should == 'hello kiwi!'")
    }

    @Test
    fun `should guarantee strings are equal`() {
        "hello kiwi".should() beEqual "hello kiwi"
    }

    @Test
    fun `should fail when string does not contain`() {
        runCatching {
            "hello kiwi".should() contain "loki"
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'hello kiwi' should contain 'loki'")
    }

    @Test
    fun `should guarantee string contains`() {
        "hello kiwi".should() contain "lo ki"
    }

    @Test
    fun `should fail when string does not start with`() {
        runCatching {
            "hello kiwi".should() startWith "ello"
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'hello kiwi' should start with 'ello'")
    }

    @Test
    fun `should guarantee string starts with`() {
        "hello kiwi".should() startWith  "hello"
    }

    @Test
    fun `should fail when string does not end with`() {
        runCatching {
            "hello kiwi".should() endWith  "kiw"
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'hello kiwi' should end with 'kiw'")
    }

    @Test
    fun `should guarantee string ends with`() {
        "hello kiwi".should() endWith  "kiwi"
    }

    @Test
    fun `should fail when string does not have proper length`() {
        runCatching {
            "hello kiwi".should() haveLength 9
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'hello kiwi' should have length 9")
    }

    @Test
    fun `should guarantee string has proper length`() {
        "hello kiwi".should() haveLength 10
    }

    @Test
    fun `should fail when string does is not capitalised`() {
        runCatching {
            "kIWI".should().beCapitalised()
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'kIWI' should be capitalised")
    }

    @Test
    fun `should guarantee string is capitalised`() {
        "Kiwi".should().beCapitalised()
    }
}