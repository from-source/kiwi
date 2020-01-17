package io.from.source.kiwi.json

import io.fromsource.kiwi.core.should
import kotlin.test.Test

class JsonStringTest {

    private val string = (0..Int.MAX_VALUE).random().toString()

    @Test
    fun `toString() should return JsonString's value`() {
        JsonString(string).toString().should beEqual """"$string""""
    }

    @Test
    fun `should verify == when JsonStrings are the same`() {
        JsonString(string).should beEqual JsonString(string)
    }

    @Test
    fun `should verify == when JsonStrings are the different`() {
        JsonString(string).should notBeEqual JsonString("something")
    }
}