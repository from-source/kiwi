package io.from.source.kiwi.jsonpath

import io.from.source.kiwi.json.JsonNumber
import io.from.source.kiwi.json.JsonObject
import io.from.source.kiwi.json.JsonParser
import io.from.source.kiwi.json.JsonString
import io.fromsource.kiwi.core.should
import kotlin.test.Ignore
import kotlin.test.Test

class JsonPathAcceptanceTest {

    private val json = JsonParser().parse("""{ 
        "store": {
            "details": {
                "name": "Books & Books"
            },
            "books": [
            { 
                "category": "science",
                "author": "Douglas Hofstadter",
                "title": "I Am a Strange Loop",
                "price": 8.95
            },
            { 
                "category": "science",
                "author": "Christophe Galfard",
                "title": "Universe in Your Hand",
                "price": 12.99
            },
            { 
                "category": "science",
                "author": "Nassim Nicholas Taleb",
                "title": "The Black Swan",
                "isbn": "978-0812973815",
                "price": 8.99
            },
            { 
                "category": "science fiction",
                "author": "Stanislaw Lem",
                "title": "Solaris",
                "isbn": "978-0156027601",
                "price": 10.99,
                "details": "Published in 1961"
            },
            { 
                "category": "fiction",
                "author": "J. R. R. Tolkien",
                "title": "The Lord of the Rings",
                "isbn": "978-0618640157",
                "price": 10.99
            }]
        }
    }""")

    @Test
    fun `should select 2nd level single value`() {
        json.evaluatePath("$.store.details.name").should be listOf(JsonString("Books & Books"))
    }

    @Test
    fun `should select all elements with single value`() {
        json.evaluatePath("$..author").should be listOf(
                JsonString("Douglas Hofstadter"),
                JsonString("Christophe Galfard"),
                JsonString("Nassim Nicholas Taleb"),
                JsonString("Stanislaw Lem"),
                JsonString(("J. R. R. Tolkien")))
    }

    @Test
    @Ignore
    fun `should select all elements by star operator`() {
        json.evaluatePath("$.store..details.*").should be listOf(
                JsonString("Books & Books"))
    }

    @Test
    fun `should select all elements with given key`() {
        json.evaluatePath("$.store..details").should be listOf(
                JsonObject(mapOf("name" to JsonString("Books & Books"))),
                JsonString("Published in 1961"))
    }

    @Test
    fun `should select single element from array by index`() {
        json.evaluatePath("$..books[0]").should be listOf(JsonObject(mapOf(
                "category" to JsonString("science"),
                "author" to JsonString("Douglas Hofstadter"),
                "title" to JsonString("I Am a Strange Loop"),
                "price" to JsonNumber(8.95))))
    }

    @Test
    @Ignore
    fun `should select last element from array`() {
        json.evaluatePath("$..books[:-1]").should be listOf(JsonObject(mapOf(
                "category" to JsonString("fiction"),
                "author" to JsonString("J. R. R. Tolkien"),
                "title" to JsonString("The Lord of the Rings"),
                "isbn" to JsonString("978-0618640157"),
                "price" to JsonNumber(10.99))))
    }

    @Test
    fun `should select multiple elements by index`() {
        json.evaluatePath("$..books[0,4]").should be listOf(
                JsonObject(mapOf(
                        "category" to JsonString("science"),
                        "author" to JsonString("Douglas Hofstadter"),
                        "title" to JsonString("I Am a Strange Loop"),
                        "price" to JsonNumber(8.95))),
                JsonObject(mapOf(
                        "category" to JsonString("fiction"),
                        "author" to JsonString("J. R. R. Tolkien"),
                        "title" to JsonString("The Lord of the Rings"),
                        "isbn" to JsonString("978-0618640157"),
                        "price" to JsonNumber(10.99))))
    }

    @Test
    @Ignore
    fun `should select multiple elements by colon`() {
        json.evaluatePath("$..books[:2]").should be listOf(
                JsonObject(mapOf(
                        "category" to JsonString("science"),
                        "author" to JsonString("Douglas Hofstadter"),
                        "title" to JsonString("I Am a Strange Loop"),
                        "price" to JsonNumber(8.95))),
                JsonObject(mapOf(
                        "category" to JsonString("fiction"),
                        "author" to JsonString("Christophe Galfard"),
                        "title" to JsonString("Universe in Your Hand"),
                        "price" to JsonNumber(12.99))))
    }

    @Test
    @Ignore
    fun `should filter elements that have key`() {
        json.evaluatePath("$..books[?(@.details)]").should be listOf(JsonObject(mapOf(
                "category" to JsonString("science"),
                "author" to JsonString("Stanislaw Lem"),
                "title" to JsonString("Solaris"),
                "isbn" to JsonString("978-0156027601"),
                "details" to JsonString("Published in 1961"),
                "price" to JsonNumber(10.99))))
    }

    @Test
    @Ignore
    fun `should filter elements by value`() {
        json.evaluatePath("$..books[?(@.price > 11)]").should be listOf(JsonObject(mapOf(
                "category" to JsonString("fiction"),
                "author" to JsonString("Christophe Galfard"),
                "title" to JsonString("Universe in Your Hand"),
                "price" to JsonNumber(12.99))))
    }

    @Test
    @Ignore
    fun `should select all element`() {
        json.evaluatePath("$..*").should be listOf(json)
    }
}