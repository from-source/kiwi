package io.from.source.kiwi.json

import io.fromsource.kiwi.core.should
import kotlin.reflect.KClass
import kotlin.test.Test

@ExperimentalStdlibApi
class JsonTest {

    @Test
    fun `should parse empty json object`() {
        val empty = "{}"
        Json.parse(empty).should() beEqual JsonObject()
    }

    @Test
    fun `should parse empty json object with spaces`() {
        val empty = " { } "
        Json.parse(empty).should() beEqual JsonObject()
    }

    @Test
    fun `should parse multiline empty json object`() {
        val empty = """{ 
                            
        }"""
        Json.parse(empty).should() beEqual JsonObject()
    }

    @Test
    fun `should throw exception when object is not terminated`() {
        runCatching {
            Json.parse("{")
        }.should()
                .beFailure(JsonException::class)
                .haveFailureMessage("Unexpected end of json")
    }
}