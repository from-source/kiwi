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
            "hello kiwi".should() contain "kiwi!"
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("'hello kiwi' should contain 'kiwi!'")
    }

    @Test
    fun `should guarantee string contains`() {
        "hello kiwi".should() contain "kiwi"
    }
}