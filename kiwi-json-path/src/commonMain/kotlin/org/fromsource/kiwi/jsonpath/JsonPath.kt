package org.fromsource.kiwi.jsonpath

import org.fromsource.kiwi.json.Json
import org.fromsource.kiwi.json.JsonArray
import org.fromsource.kiwi.json.JsonObject

object JsonPath {
    private const val root = "$"
    private const val recursive = ".."
    private const val child = "."
    private const val startArray = '['
    private const val closeArray = ']'

    fun evaluate(json: Json, path: String): List<Json> {
        val head = path.head()
        if (head != root) {
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
            path.startsWith(recursive) -> {
                val result = elements.flatMap { recursive(it, emptyList()) }
                evaluate(result, path.drop(recursive.length))
            }
            path.startsWith(child) -> evaluate(elements, path.drop(child.length))
            path.startsWith(startArray) -> {
                val expression = path.between(startArray, closeArray)
                val selector = Selector.create(expression)
                val result = evaluateArray(selector, elements)
                evaluate(result, path.drop(expression.length + 2))
            }
            else -> {
                val selector = path.split(child).filter { it.isNotBlank() }.head().takeWhile { it != startArray }
                val result = elements.mapNotNull { json -> select(json, selector) }
                evaluate(result, path.drop(selector.length))
            }
        }
    }

    private fun evaluateArray(selector: Selector, elements: List<Json>): List<Json> {
        val arrays = elements.filterIsInstance<JsonArray>()
        return when (selector) {
            is SingleIndex -> arrays.filter { array -> array.size() > selector.value() }.map { array -> array.values()[selector.value()] }
            is MultipleIndex -> selector.indexes().flatMap { index -> evaluateArray(index, elements) }
            is Slice -> selector.indexes().flatMap { index -> evaluateArray(index, elements) }
            is All -> arrays.flatMap { array -> array.values() }
            is NoOp -> emptyList()
        }
    }

    private fun select(json: Json, selector: String): Json? {
        return when (json) {
            is JsonObject -> json.value(selector)
            else -> null
        }
    }

    private fun recursive(json: Json, selected: List<Json>): List<Json> {
        return when (json) {
            is JsonObject -> {
                selected + json + json.values().flatMap { value -> recursive(value, emptyList()) }
            }
            is JsonArray -> {
                selected + json + json.values().flatMap { value -> recursive(value, emptyList()) }
            }
            else -> selected
        }
    }
}
