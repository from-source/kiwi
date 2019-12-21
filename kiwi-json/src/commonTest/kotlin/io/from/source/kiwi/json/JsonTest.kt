package io.from.source.kiwi.json

import io.fromsource.kiwi.core.should
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

    @Test
    fun `should throw exception when token can not be recognized when parsing object`() {
        runCatching {
            Json.parse("{.}")
        }.should()
                .beFailure(JsonException::class)
                .haveFailureMessage("Unrecognized character '.'")
    }

    @Test
    fun `should throw exception when token can not be recognized before parsing`() {
        runCatching {
            Json.parse("a {}")
        }.should()
                .beFailure(JsonException::class)
                .haveFailureMessage("Unrecognized character 'a'")
    }

    @Test
    fun `should parse json with single string element`() {
        val json = """{ 
            "key":"value"                    
        }"""

        Json.parse(json).should() beEqual JsonObject(mapOf("key" to JsonString("value")))
    }
}