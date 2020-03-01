package io.fromsource.kiwi.json

import io.fromsource.kiwi.core.should
import kotlin.test.Test

class JsonNumberTest {

    private val number = (0..Int.MAX_VALUE).random()

    @Test
    fun `toString() should return JsonNumber's value`() {
        JsonNumber(number).toString().should beEqual "$number"
    }

    @Test
    fun `should verify == when JsonNumbers are the same`() {
        JsonNumber(number).should beEqual JsonNumber(number)
    }

    @Test
    fun `should verify == when JsonNumbers are different`() {
        JsonNumber(number).should notBeEqual JsonNumber(-number)
    }
}