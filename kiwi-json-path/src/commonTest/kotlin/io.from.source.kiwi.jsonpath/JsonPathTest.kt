package io.from.source.kiwi.jsonpath

import io.from.source.kiwi.json.JsonParser
import io.from.source.kiwi.json.JsonString
import io.fromsource.kiwi.core.should
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
    fun `should select json 1st level property`() {
        val json = parser.parse("""{
            "book": "The Lord of the Rings"
        }""")

        json.evaluatePath("$.book").should
                .contain(JsonString("The Lord of the Rings"))
                .haveSize(1)
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
}