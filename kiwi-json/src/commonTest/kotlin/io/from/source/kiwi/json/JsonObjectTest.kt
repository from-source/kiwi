package io.from.source.kiwi.json

import io.fromsource.kiwi.core.should
import kotlin.test.Test

class JsonObjectTest {
    private val jsonNumber = JsonNumber(1)
    private val jsonBoolean = JsonBoolean(true)
    private val jsonString = JsonString("something")

    val object1 = JsonObject(mapOf(
            Pair("number", jsonNumber),
            Pair("boolean", jsonBoolean),
            Pair("string", jsonString)))

    @Test
    fun `toString() should return JsonObject's value()`() {
        object1.toString().should().beEqual("""{"number": 1, "boolean": true, "string": "something"}""")
    }

    @Test
    fun `should verify == when JsonObjects are the same`() {
        val object2 = JsonObject(mapOf(
                Pair("number", jsonNumber).copy(),
                Pair("boolean", jsonBoolean).copy(),
                Pair("string", jsonString).copy()))

        object1.should() beEqual object2
    }

    @Test
    fun `should verify == when JsonArray are different`() {
        val object2 = JsonObject(mapOf(
                Pair("number", jsonNumber),
                Pair("boolean", jsonBoolean.copy(false)),
                Pair("string", jsonString)))

        object1.should() notBeEqual object2
    }
}