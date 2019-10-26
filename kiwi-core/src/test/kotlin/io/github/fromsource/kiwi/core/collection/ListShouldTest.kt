package io.github.fromsource.kiwi.core.collection

import io.github.fromsource.kiwi.core.should
import io.github.fromsource.kiwi.core.util.runCatching
import org.junit.jupiter.api.Test

class ListShouldTest {

    @Test
    fun `should throw AssertionError when list does not have expected size`() {
        val numbers = listOf(1, 2, 3)

        runCatching {
            numbers.should() haveSize numbers.size - 1
        }.should()
                .beFailure(AssertionError::class)
                .haveFailureMessage("Expected size: 2, actual: 3")
    }

    @Test
    fun `should guarantee list have`() {
        val numbers = listOf(1, 2, 3)

        numbers.should() haveSize 3
    }
}



