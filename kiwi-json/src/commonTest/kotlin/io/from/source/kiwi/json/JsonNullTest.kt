package io.from.source.kiwi.json

import io.fromsource.kiwi.core.should
import kotlin.test.Test

class JsonNullTest {

    @Test
    fun `should return "null" for toString()`() {
        JsonNull.toString().should() beEqual "null"
    }
}