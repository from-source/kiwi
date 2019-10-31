package io.github.fromsource.kiwi.core.collection

import io.github.fromsource.kiwi.core.should
import io.github.fromsource.kiwi.core.util.runCatching
import org.junit.jupiter.api.Test

class MapShouldTest {

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
}