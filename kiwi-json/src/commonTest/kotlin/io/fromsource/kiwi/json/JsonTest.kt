package io.fromsource.kiwi.json

import io.fromsource.kiwi.core.should
import kotlin.test.Test

@ExperimentalStdlibApi
class JsonTest {

    @Test
    fun `should parse empty json object`() {
        val empty = "{}"
        Json.parse(empty).should beEqual JsonObject()
    }

    @Test
    fun `should parse empty json object with spaces`() {
        val empty = " { } "
        Json.parse(empty).should beEqual JsonObject()
    }

    @Test
    fun `should parse multiline empty json object`() {
        val empty = """{ 
                            
        }"""
        Json.parse(empty).should beEqual JsonObject()
    }

    @Test
    fun `should throw exception when object is not terminated`() {
        runCatching {
            Json.parse("{")
        }.should
                .beFailure(JsonException::class)
                .haveFailureMessage("Unexpected end of json")
    }

    @Test
    fun `should throw exception when token can not be recognized when parsing object`() {
        runCatching {
            Json.parse("{.}")
        }.should
                .beFailure(JsonException::class)
                .haveFailureMessage("Unrecognized character '.'")
    }

    @Test
    fun `should throw exception when token can not be recognized before parsing`() {
        runCatching {
            Json.parse("a {}")
        }.should
                .beFailure(JsonException::class)
                .haveFailureMessage("Unrecognized character 'a'")
    }

    @Test
    fun `should throw exception when for false positive json`() {
        runCatching {
            Json.parse("{} {}")
        }.should
                .beFailure(JsonException::class)
                .haveFailureMessage("Unexpected character '{'")
    }

    @Test
    fun `should parse json with single string element`() {
        val json = """{ 
            "key":"value"                    
        }"""

        Json.parse(json).should beEqual JsonObject(mapOf("key" to JsonString("value")))
    }

    @Test
    fun `should parse json with single string element what has escaped characters`() {
        val json = """{ 
            "key" : "\"inner quote\" value"                    
        }"""

        Json.parse(json).should beEqual JsonObject(mapOf("key" to JsonString("\\\"inner quote\\\" value")))
    }

    @Test
    fun `should parse json with single string element contains backslash`() {
        val json = """{"backslash" : "\\"}"""

        Json.parse(json).should beEqual JsonObject(mapOf("backslash" to JsonString("""\\""")))
    }

    @Test
    fun `should parse json with single string element contains backslash2`() {
        val json = """{"backslash and escaped quote" : " \\\" "}"""

        Json.parse(json).should beEqual JsonObject(mapOf("backslash and escaped quote" to JsonString(""" \\\" """)))
    }

    @Test
    fun `should parse json with single null element`() {
        val json = """{ "key": null }"""

        Json.parse(json).should beEqual JsonObject(mapOf("key" to JsonNull))
    }

    @Test
    fun `should throw exception when coma is not followed by name value pair`() {
        runCatching {
            Json.parse("""{ "key":"value", }""")
        }.should
                .beFailure(JsonException::class)
                .haveFailureMessage("Unrecognized character '}'")
    }

    @Test
    fun `should throw exception when name value pair is not separated by colon`() {
        runCatching {
            Json.parse("""{ "key";"value"}""")
        }.should
                .beFailure(JsonException::class)
                .haveFailureMessage("Unrecognized character ';'")
    }

    @Test
    fun `should parse json with multiple string element`() {
        val json = """{ 
            "key":  "value",                    
            "some": "other"                    
        }"""

        Json.parse(json).should beEqual JsonObject(mapOf(
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

        Json.parse(json).should.beEqual(JsonObject(mapOf(
                "some" to JsonString("other"),
                "and" to JsonString("another")
        )))
    }

    @Test
    fun `should parse json with false boolean value`() {
        val json = """{ 
            "false or true": false     
        }"""

        Json.parse(json).should.beEqual(JsonObject(mapOf(
                "false or true" to JsonBoolean(false)
        )))
    }

    @Test
    fun `should parse json with true boolean value`() {
        val json = """{ 
            "false or true": true     
        }"""

        Json.parse(json).should.beEqual(JsonObject(mapOf(
                "false or true" to JsonBoolean(true)
        )))
    }

    @Test
    fun `should throw exception when booleam value can not parsed`() {
        runCatching {
            Json.parse("""{ "bool": fals }""")
        }.should
                .beFailure(JsonException::class)
                .haveFailureMessage("Unrecognized boolean value")
    }

    @Test
    fun `should parse json with integer number value`() {
        val json = """{ 
            "number": 1987     
        }"""

        Json.parse(json).should.beEqual(JsonObject(mapOf(
                "number" to JsonNumber(1987L)
        )))
    }

    @Test
    fun `should parse json with negative integer number value`() {
        val json = """{ 
            "number": -1987     
        }"""

        Json.parse(json).should.beEqual(JsonObject(mapOf(
                "number" to JsonNumber(-1987L)
        )))
    }

    @Test
    fun `should parse json with nested json object`() {
        val json = """{ 
            "object": {}     
        }"""

        Json.parse(json).should.beEqual(JsonObject(mapOf(
                "object" to JsonObject()
        )))
    }

    @Test
    fun `should parse json object with array as a value`() {
        val json = """{ 
            "array": []     
        }"""

        Json.parse(json).should.beEqual(JsonObject(mapOf(
                "array" to JsonArray()
        )))
    }

    @Test
    fun `should parse json object that contains basic types and nested objects`() {
        val json = """{
            "widget": {
                "debug": "on",
                "window": {
                    "title": "Sample Konfabulator Widget"
                },
                "image": { 
                    "src": "Images/Sun.png",
                    "vOffset": 1024
                },
                "text": {
                    "alignment": false,
                    "onMouseUp": "sun1.opacity = (sun1.opacity / 100) * 90;"
                }
        }}""".trimIndent()

        Json.parse(json).should.beEqual(JsonObject(mapOf(
                "widget" to JsonObject(mapOf(
                        "debug" to JsonString("on"),
                        "window" to JsonObject(mapOf(
                                "title" to JsonString("Sample Konfabulator Widget")
                        )),
                        "image" to JsonObject(mapOf(
                                "src" to JsonString("Images/Sun.png"),
                                "vOffset" to JsonNumber(1024L)
                        )),
                        "text" to JsonObject(mapOf(
                                "alignment" to JsonBoolean(false),
                                "onMouseUp" to JsonString("sun1.opacity = (sun1.opacity / 100) * 90;")
                        ))
                ))
        )))
    }

    @Test
    fun `should parse empty array`() {
        val empty = "[]"
        Json.parse(empty).should beEqual JsonArray()
    }

    @Test
    fun `should parse empty array with spaces`() {
        val empty = " [ ] "
        Json.parse(empty).should beEqual JsonArray()
    }

    @Test
    fun `should parse multiline empty array`() {
        val empty = """[ 
                            
        ]"""
        Json.parse(empty).should beEqual JsonArray()
    }

    @Test
    fun `should throw exception when array is not terminated`() {
        runCatching {
            Json.parse("[")
        }.should
                .beFailure(JsonException::class)
                .haveFailureMessage("Unexpected end of json")
    }

    @Test
    fun `should throw exception when token can not be recognized when parsing array`() {
        runCatching {
            Json.parse("[x]")
        }.should
                .beFailure(JsonException::class)
                .haveFailureMessage("Unrecognized character 'x'")
    }

    @Test
    fun `should parse array with single string value`() {
        val array = """[ "text value" ]"""

        Json.parse(array).should beEqual JsonArray(arrayListOf(JsonString("text value")))
    }

    @Test
    fun `should parse array with single int value`() {
        val array = """[ 1 ]"""

        Json.parse(array).should beEqual JsonArray(arrayListOf(JsonNumber(1L)))
    }

    @Test
    fun `should parse array with single negative int value`() {
        val array = """[ -1 ]"""

        Json.parse(array).should beEqual JsonArray(arrayListOf(JsonNumber(-1L)))
    }

    @Test
    fun `should parse array with single decimal value`() {
        val array = """[ 10.01  ]"""

        Json.parse(array).should beEqual JsonArray(arrayListOf(JsonNumber(10.01)))
    }

    @Test
    fun `should parse array with single decimal -e value`() {
        val array = """[ 10e-2  ]"""

        Json.parse(array).should beEqual JsonArray(arrayListOf(JsonNumber(0.1)))
    }

    @Test
    fun `should parse array with single decimal +e value`() {
        val array = """[ 10E+2  ]"""

        Json.parse(array).should beEqual JsonArray(arrayListOf(JsonNumber(1000.0)))
    }

    @Test
    fun `should parse array with single integer +e value`() {
        val array = """[ 10.01e+2  ]"""

        Json.parse(array).should beEqual JsonArray(arrayListOf(JsonNumber(1001.0)))
    }

    @Test
    fun `should parse array with single integer -e value to decimal`() {
        val array = """[ 1e-2  ]"""

        Json.parse(array).should beEqual JsonArray(arrayListOf(JsonNumber(0.01)))
    }

    @Test
    fun `should parse array with single integer -e`() {
        val array = """[ 100e-2  ]"""

        Json.parse(array).should beEqual JsonArray(arrayListOf(JsonNumber(1.0)))
    }

    @Test
    fun `should parse array with single boolean value`() {
        val array = """[ false ]"""

        Json.parse(array).should beEqual JsonArray(arrayListOf(JsonBoolean(false)))
    }

    @Test
    fun `should parse array with single null value`() {
        val array = """[ null ]"""

        Json.parse(array).should beEqual JsonArray(arrayListOf(JsonNull))
    }

    @Test
    fun `should throw exception when parsing invalid null`() {
        runCatching {
            Json.parse("[ nil ]")
        }.should
                .beFailure(JsonException::class)
                .haveFailureMessage("Unrecognized value 'nil'")
    }

    @Test
    fun `should throw exception when parsing invalid value starting with null`() {
        runCatching {
            Json.parse("[ nullable ]")
        }.should
                .beFailure(JsonException::class)
                .haveFailureMessage("Unrecognized character 'a'")
    }

    @Test
    fun `should parse array with single json object value`() {
        val array = """[ { "some": "value" } ]"""

        Json.parse(array).should beEqual JsonArray(arrayListOf(JsonObject(mapOf("some" to JsonString("value")))))
    }

    @Test
    fun `should parse nested array`() {
        val array = """[ [], [] ]"""

        Json.parse(array).should beEqual JsonArray(arrayListOf(
                JsonArray(),
                JsonArray()
        ))
    }

    @Test
    fun `should parse multiple array value`() {
        val array = """[ true , 
            false  
            ]"""

        Json.parse(array).should beEqual JsonArray(arrayListOf(
                JsonBoolean(true),
                JsonBoolean(false)
        ))
    }

    @Test
    fun `should throw exception when values are terminates by coma`() {
        runCatching {
            Json.parse("""[ false, true , ]""")
        }.should
                .beFailure(JsonException::class)
                .haveFailureMessage("Unrecognized character ']'")
    }

    @Test
    fun `should parse array with multi values`() {
        val array = """[ "text", "another value", false, 1, null, { "some": "value" }, [ "text" ] ]"""

        Json.parse(array).should beEqual JsonArray(arrayListOf(
                JsonString("text"),
                JsonString("another value"),
                JsonBoolean(false),
                JsonNumber(1L),
                JsonNull,
                JsonObject(mapOf("some" to JsonString("value"))),
                JsonArray(arrayListOf(JsonString("text")))
        ))
    }

    @Test
    fun `should parse int number as a valid json`() {
        val number = "12"

        Json.parse(number).should beEqual JsonNumber(12L)
    }

    @Test
    fun `should parse double number as a valid json`() {
        Json.parse("-10.2e+2").should beEqual JsonNumber(-1020.0)
    }

    @Test
    fun `should parse string as a valid json`() {
        Json.parse(""" "I am string" """).should beEqual JsonString("I am string")
    }

    @Test
    fun `should parse boolean as a valid json`() {
        Json.parse("false").should beEqual JsonBoolean(false)
    }
}