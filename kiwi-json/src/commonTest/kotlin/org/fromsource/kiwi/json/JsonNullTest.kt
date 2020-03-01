package org.fromsource.kiwi.json

import org.fromsource.kiwi.core.should
import kotlin.test.Test

class JsonNullTest {

    @Test
    fun `should return "null" for toString()`() {
        JsonNull.toString().should beEqual "null"
    }
}