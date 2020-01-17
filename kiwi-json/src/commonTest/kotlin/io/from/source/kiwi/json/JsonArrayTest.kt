package io.from.source.kiwi.json

import io.fromsource.kiwi.core.should
import kotlin.test.Test

class JsonArrayTest {
    private val jsonNumber = JsonNumber(1)
    private val jsonBoolean = JsonBoolean(true)
    private val jsonString = JsonString("something")

    @Test
    fun `toString() should return JsonArray's value()`() {
        JsonArray(arrayListOf(jsonNumber, jsonBoolean, jsonString)).toString().should
                .beEqual("""[1, true, "something"]""")
    }

    @Test
    fun `should verify == when JsonArray are the same`() {
        val array1 = JsonArray(arrayListOf(jsonNumber, jsonBoolean, jsonString))
        val array2 = JsonArray(arrayListOf(jsonNumber.copy(), jsonBoolean.copy(), jsonString.copy()))

        array1.should beEqual array2
    }

    @Test
    fun `should verify == when JsonArray are different`() {
        val array1 = JsonArray(arrayListOf(jsonNumber, jsonBoolean, jsonString))
        val array2 = JsonArray(arrayListOf(jsonNumber.copy(), JsonBoolean(false), jsonString.copy()))

        array1.should notBeEqual array2
    }

    @Test
    fun `add add new element to the array`() {
        val array = JsonArray(arrayListOf(JsonBoolean(true)))

        val updated = array + JsonBoolean(false)

        updated.should beEqual JsonArray(arrayListOf(JsonBoolean(true), JsonBoolean(false)))
    }
}