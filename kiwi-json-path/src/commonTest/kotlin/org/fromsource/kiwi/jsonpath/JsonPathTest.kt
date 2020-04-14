package org.fromsource.kiwi.jsonpath

import org.fromsource.kiwi.core.should
import org.fromsource.kiwi.json.JsonObject
import org.fromsource.kiwi.json.JsonParser
import org.fromsource.kiwi.json.JsonString
import kotlin.test.Test

class JsonPathTest {
    private val parser = JsonParser()

    @Test
    fun `should select whole json by $`() {
        val json = parser.parse("{}")

        json.evaluatePath("$").should
                .contain(json)
                .haveSize(1)
    }

    @Test
    fun `should throw exception when path does not start with $`() {
        val json = parser.parse("{}")

        runCatching {
            json.evaluatePath(".")
        }.should
                .beFailure(JsonPathException::class)
                .haveFailureMessage("Json path should start with '$'")
    }

    @Test
    fun `should select json 1st level property`() {
        val json = parser.parse("""{
            "book": "The Lord of the Rings"
        }""")

        json.evaluatePath("$.book").should
                .contain(JsonString("The Lord of the Rings"))
                .haveSize(1)
    }

    @Test
    fun `should not select element if path does not exist`() {
        val json = parser.parse("""{
            "book": "The Lord of the Rings"
        }""")

        json.evaluatePath("$.nothing").should
                .beEmpty()
    }

    @Test
    fun `should select json n level property`() {
        val json = parser.parse("""{
            "book": {
                "title": "The Lord of the Rings"
            }
        }""")

        json.evaluatePath("$.book.title").should
                .contain(JsonString("The Lord of the Rings"))
                .haveSize(1)
    }

    @Test
    fun `should find recursive descent from root element`() {
        val json = parser.parse("""{
            "book": {
                "title": "The Lord of the Rings"
            }
        }""")

        json.evaluatePath("$..").should
                .contain(
                        json,
                        JsonObject(mapOf("title" to JsonString("The Lord of the Rings"))))
                .haveSize(2)
    }

    @Test
    fun `should find recursively all elements for single json`() {
        val json = parser.parse("""{
            "book": {
                "title": "The Lord of the Rings"
            }
        }""")

        json.evaluatePath("$..book").should
                .contain(JsonObject(mapOf("title" to JsonString("The Lord of the Rings"))))
                .haveSize(1)
    }

    @Test
    fun `should find recursively all elements for nested json`() {
        val json = parser.parse("""{
            "new": {
                "book": {
                    "title": "The Lord of the Rings"
                }
            },
            "used": {
                "comics": "Solaris",
                "details": {}
            }
        }""")

        json.evaluatePath("$..").should
                .contain(
                        json,
                        JsonObject(mapOf("book" to JsonObject(mapOf("title" to JsonString("The Lord of the Rings"))))),
                        JsonObject(mapOf("title" to JsonString("The Lord of the Rings"))),
                        JsonObject(mapOf("comics" to JsonString("Solaris"), "details" to JsonObject())),
                        JsonObject()
                )
                .haveSize(5)
    }

    @Test
    fun `should find recursively all elements by name`() {
        val json = parser.parse("""{
            "new": {
                "book": {
                    "title": "The Lord of the Rings"
                }
            },
            "used": {
                "book": "Solaris",
                "details": {}
            }
        }""")

        json.evaluatePath("$..book").should
                .contain(
                        JsonObject(mapOf("title" to JsonString("The Lord of the Rings"))),
                        JsonString("Solaris"))
                .haveSize(2)
    }

    @Test
    fun `should not find recursively all elements when it does not exist`() {
        val json = parser.parse("""{
            "new": {
                "book": {
                    "title": "The Lord of the Rings"
                }
            },
            "used": {
                "book": "Solaris",
                "details": {}
            }
        }""")

        json.evaluatePath("$..nothing").should
                .beEmpty()
    }

    @Test
    fun `should find recursively firstly and next by name`() {
        val json = parser.parse("""{
            "new": {
                "book": {
                    "title": "The Lord of the Rings"
                }
            },
            "used": {
                "book": "Solaris",
                "details": {}
            }
        }""")

        json.evaluatePath("$..book.title").should
                .contain(
                        JsonString("The Lord of the Rings"))
                .haveSize(1)
    }

    @Test
    fun `should select array by index`() {
        val json = parser.parse("""[
            "Solaris",
            "The Lord of the Rings",
            "The Black Swan"
        ]""")

        json.evaluatePath("$.[0]").should
                .contain(JsonString("Solaris"))
                .haveSize(1)
    }

    @Test
    fun `should select array by name and index`() {
        val json = parser.parse("""{
            "read": [
                "Solaris",
                "The Lord of the Rings",
                "The Black Swan"
            ]}""")

        json.evaluatePath("$.read[0]").should
                .contain(JsonString("Solaris"))
                .haveSize(1)
    }

    @Test
    fun `should not select element if array does not have index`() {
        val json = parser.parse("""[
            "Solaris",
            "The Lord of the Rings",
            "The Black Swan"
        ]""")

        json.evaluatePath("$.read[3]").should
                .beEmpty()
    }

    @Test
    fun `should select array by name and multiple indexes`() {
        val json = parser.parse("""{
            "read": [
                "Solaris",
                "The Lord of the Rings",
                "The Black Swan"
            ]}""")

        json.evaluatePath("$.read[0,2]").should
                .contain(JsonString("Solaris"))
                .contain(JsonString("The Black Swan"))
                .haveSize(2)
    }

    @Test
    fun `should return selected elements in index order`() {
        val json = parser.parse("""{
            "read": [
                "Solaris",
                "The Lord of the Rings",
                "The Black Swan"
            ]}""")

        json.evaluatePath("$.read[2,0]").should
                .be(listOf(JsonString("The Black Swan"), JsonString("Solaris")))
    }

    @Test
    fun `should ignore whitespaces when selecting array`() {
        val json = parser.parse("""{
            "read": [
                "Solaris",
                "The Lord of the Rings",
                "The Black Swan"
            ]}""")

        json.evaluatePath("$.read[2, 0]").should
                .be(listOf(JsonString("The Black Swan"), JsonString("Solaris")))
    }

    @Test
    fun `should select elements in array by range selector`() {
        val json = parser.parse("""{
            "read": [
                "Solaris",
                "The Lord of the Rings",
                "The Black Swan"
            ]}""")

        json.evaluatePath("$.read[:2]").should
                .be(listOf(JsonString("Solaris"), JsonString("The Lord of the Rings")))
    }

    @Test
    fun `should select elements in array by backward range selector`() {
        val json = parser.parse("""{
            "read": [
                "Solaris",
                "The Lord of the Rings",
                "The Black Swan"
            ]}""")

        json.evaluatePath("$.read[:-2]").should
                .be(listOf(JsonString("The Lord of the Rings"), JsonString("The Black Swan")))
    }

    @Test
    fun `should select elements in array by backward range selector when index exceeded its range`() {
        val json = parser.parse("""{
            "read": [
                "Solaris",
                "The Lord of the Rings",
                "The Black Swan"
            ]}""")

        json.evaluatePath("$.read[:-4]").should
                .be(listOf(JsonString("Solaris"), JsonString("The Lord of the Rings"), JsonString("The Black Swan")))
    }

    @Test
    fun `should select elements in array by range operator when range selector array size`() {
        val json = parser.parse("""{
            "read": [
                "Solaris",
                "The Lord of the Rings",
                "The Black Swan"
            ]}""")

        json.evaluatePath("$.read[:4]").should
                .be(listOf(JsonString("Solaris"), JsonString("The Lord of the Rings"), JsonString("The Black Swan")))
    }

    @Test
    fun `should select all elements in array`() {
        val json = parser.parse("""{
            "read": [
                "Solaris",
                "The Lord of the Rings",
                "The Black Swan"
            ]}""")

        json.evaluatePath("$.read[*]").should
                .be(listOf(JsonString("Solaris"), JsonString("The Lord of the Rings"), JsonString("The Black Swan")))
    }

    @Test
    fun `should ignore whitespaces when selecting all elements in array`() {
        val json = parser.parse("""{
            "read": [
                "Solaris",
                "The Lord of the Rings",
                "The Black Swan"
            ]}""")

        json.evaluatePath("$.read[ * ]").should
                .be(listOf(JsonString("Solaris"), JsonString("The Lord of the Rings"), JsonString("The Black Swan")))
    }
}