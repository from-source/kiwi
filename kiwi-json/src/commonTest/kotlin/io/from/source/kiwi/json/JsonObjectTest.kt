package io.from.source.kiwi.json

import io.fromsource.kiwi.core.should
import kotlin.test.Test

class JsonObjectTest {
    private val jsonNumber = JsonNumber(1)
    private val jsonBoolean = JsonBoolean(true)
    private val jsonString = JsonString("something")

    val object1 = JsonObject(mapOf(
            "number" to jsonNumber,
            "boolean" to jsonBoolean,
            "string" to jsonString
    ))

    @Test
    fun `toString() should return JsonObject's value()`() {
        object1.toString().should().beEqual("""{"number": 1, "boolean": true, "string": "something"}""")
    }

    @Test
    fun `should verify == when JsonObjects are the same`() {
        val object2 = JsonObject(mapOf(
                "number" to jsonNumber.copy(),
                "boolean" to jsonBoolean.copy(),
                "string" to jsonString.copy()
        ))

        object1.should() beEqual object2
    }

    @Test
    fun `should verify == when JsonArray are different`() {
        val object2 = JsonObject(mapOf(
                "number" to jsonNumber,
                "boolean" to jsonBoolean.copy(false),
                "string" to jsonString
        ))

        object1.should() notBeEqual object2
    }

    @Test
    fun `should add empty JsonObject to empty JsonObject`() {
        JsonObject().should() beEqual JsonObject() + JsonObject()
    }

    @Test
    fun `should add non empty JsonObject to empty JsonObject`() {
        object1.should() beEqual object1 + JsonObject()
    }

    @Test
    fun `should add non empty JsonObject to non empty JsonObject when there is not conflict in keys`() {
        val object3 = JsonObject(mapOf("value" to jsonNumber))

        (object1 + object3).should() beEqual JsonObject(mapOf(
                "number" to jsonNumber,
                "boolean" to jsonBoolean,
                "string" to jsonString,
                "value" to jsonNumber))
    }

    @Test
    fun `should take right JsonObject's key in case of conflict`() {
        val object3 = JsonObject(mapOf("boolean" to JsonBoolean(false)))
        val object4 = JsonObject(mapOf("boolean" to JsonBoolean(true)))

        (object3 + object4).should() beEqual JsonObject(mapOf("boolean" to JsonBoolean(true)))
    }
}