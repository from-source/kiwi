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

    @Test
    fun `should throw exception when coma is not followed by name value pair`() {
        runCatching {
            Json.parse("""{ "key":"value", }""")
        }.should()
                .beFailure(JsonException::class)
                .haveFailureMessage("Unrecognized character '}'")
    }

    @Test
    fun `should throw exception when name value pair is not separated by colon`() {
        runCatching {
            Json.parse("""{ "key";"value"}""")
        }.should()
                .beFailure(JsonException::class)
                .haveFailureMessage("Unrecognized character ';'")
    }

    @Test
    fun `should parse json with multiple string element`() {
        val json = """{ 
            "key":  "value",                    
            "some": "other"                    
        }"""

        Json.parse(json).should() beEqual JsonObject(mapOf(
                "key" to JsonString("value"),
                "some" to JsonString("other")
        ))
    }

    @Test
    fun `should parse json with multiple string elements where values are in separate lines`() {
        val json = """{ 
            "some"  :     
            "other" ,
            "and"   :
            "another"
        }"""

        Json.parse(json).should().beEqual(JsonObject(mapOf(
                "some" to JsonString("other"),
                "and" to JsonString("another")
        )))
    }

    @Test
    fun `should parse json with false boolean value`() {
        val json = """{ 
            "false or true": false     
        }"""

        Json.parse(json).should().beEqual(JsonObject(mapOf(
                "false or true" to JsonBoolean(false)
        )))
    }

    @Test
    fun `should parse json with true boolean value`() {
        val json = """{ 
            "false or true": true     
        }"""

        Json.parse(json).should().beEqual(JsonObject(mapOf(
                "false or true" to JsonBoolean(true)
        )))
    }

    @Test
    fun `should throw exception when booleam value can not parsed`() {
        runCatching {
            Json.parse("""{ "bool": fals }""")
        }.should()
                .beFailure(JsonException::class)
                .haveFailureMessage("Unrecognized boolean value")
    }

    @Test
    fun `should parse json with integer number value`() {
        val json = """{ 
            "number": 1987     
        }"""

        Json.parse(json).should().beEqual(JsonObject(mapOf(
                "number" to JsonNumber(1987L)
        )))
    }
}