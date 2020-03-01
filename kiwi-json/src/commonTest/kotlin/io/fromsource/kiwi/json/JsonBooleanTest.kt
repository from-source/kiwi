package io.fromsource.kiwi.json

import io.fromsource.kiwi.core.should
import kotlin.test.Test

class JsonBooleanTest {

    @Test
    fun `toString() should return JsonBoolean's value()`() {
        JsonBoolean(false).toString().should beEqual "false"
    }

    @Test
    fun `should verify == when JsonBooleans are the same - false`() {
        JsonBoolean(false).should beEqual JsonBoolean(false)
    }

    @Test
    fun `should verify == when JsonBooleans are the same - true`() {
        JsonBoolean(true).should beEqual JsonBoolean(true)
    }

    @Test
    fun `should verify == when JsonBooleans are different`() {
        JsonBoolean(false).should notBeEqual JsonBoolean(true)
    }
}