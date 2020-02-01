package io.from.source.kiwi.jsonpath

import io.from.source.kiwi.json.JsonParser
import io.fromsource.kiwi.core.should
import kotlin.test.Ignore
import kotlin.test.Test

class JsonPathAcceptanceTest {

    private val json = JsonParser().parse("""{ 
        "store": {
            "details": {
                "name": "Books & Books"
            }
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
                "price": 10.99
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
    @Ignore
    fun `should select 2nd level single value`() {
        json.path("$.store.details.name").should be ("Books & Books")
    }

    @Test
    @Ignore
    fun `should select all elements with single value`() {
        json.path("$..author").should be (arrayListOf("Douglas Hofstadter", "Christophe Galfard", "Nassim Nicholas Taleb", "Stanislaw Lem", "J. R. R. Tolkien"))
    }
}