package io.from.source.kiwi.jsonpath

import io.from.source.kiwi.json.Json
import io.from.source.kiwi.json.JsonArray
import io.from.source.kiwi.json.JsonObject

object JsonPath {
    fun evaluate(json: Json, path: String): List<Json> {
        val head = path.head()
        if (head != "$") {
            throw JsonPathException("Json path should start with '\$'")
        }
        val tail = path.tail()
        return evaluate(listOf(json), tail)
    }

    private tailrec fun evaluate(elements: List<Json>, path: String): List<Json> {
        if (path.isBlank()) {
            return elements
        }
        return when {
            path.startsWith("..") -> {
                val result = elements.flatMap { recursive(it, emptyList()) }
                evaluate(result, path.drop(2))
            }
            path.startsWith(".") -> evaluate(elements, path.drop(1))
            else -> {
                val selector = path.split(".").filter { it.isNotBlank() }.head()
                val result = elements.map { select(it, selector) }.filterNotNull()
                evaluate(result, path.drop(selector.length))
            }
        }
    }

    private fun select(json: Json, selector: String): Json? {
        return when (json) {
            is JsonObject -> json.value[selector]
            else -> null
        }
    }

    private fun recursive(json: Json, selected: List<Json>): List<Json> {
        return when (json) {
            is JsonObject -> {
                selected + json + json.value.values.flatMap { value -> recursive(value, emptyList()) }
            }
            is JsonArray -> {
                selected + json + json.value.flatMap { value -> recursive(value, emptyList()) }
            }
            else -> selected
        }
    }
}

private fun <E> List<E>.tail(): List<E> = if (this.isEmpty()) throw RuntimeException("Empty list") else this.drop(1)
private fun <E> List<E>.head(): E = this.first()

private fun String.tail(): String = if (this.isEmpty()) throw RuntimeException("Empty string") else this.drop(1)
private fun String.head(): String = this.take(1)