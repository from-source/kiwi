package io.from.source.kiwi.jsonpath

import io.from.source.kiwi.json.Json
import io.from.source.kiwi.json.JsonArray
import io.from.source.kiwi.json.JsonObject

object JsonPath {
    private const val root = "$"
    private const val recursive = ".."
    private const val child = "."
    private const val startArray = '['
    private const val closeArray = ']'
    private const val slice = ':'

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
                val selector = path.between(startArray, closeArray)
                val result = evaluateArray(selector, elements)
                evaluate(result, path.drop(selector.length + 2))
            }
            else -> {
                val selector = path.split(child).filter { it.isNotBlank() }.head().takeWhile { it != startArray }
                val result = elements.mapNotNull { json -> select(json, selector) }
                evaluate(result, path.drop(selector.length))
            }
        }
    }

    private fun evaluateArray(selector: String, elements: List<Json>): List<Json> {
        val arrays = elements.filterIsInstance<JsonArray>()
        return when {
            isSingleIndex(selector) -> arrays.filter { array -> array.size() > selector.toInt() }.map { array -> array.values()[selector.toInt()] }
            isMultipleIndexes(selector) -> indexes(selector).flatMap { index -> evaluateArray(index, elements) }
            isSlice(selector) -> {
                val last = selector.tail().toInt() - 1
                (0..last).map { it.toString() }.flatMap { index -> evaluateArray(index, elements) }
            }
            else -> emptyList()
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

    private fun isMultipleIndexes(selector: String): Boolean =
            selector.split(",").filter { it.isNotBlank() }.map { it.trim() }.all { isSingleIndex(it) }

    private fun isSingleIndex(selector: String): Boolean =
            selector.toIntOrNull()?.let { true } ?: false

    private fun isSlice(selector: String): Boolean =
            selector.startsWith(slice) && isSingleIndex(selector.tail())

    private fun indexes(selector: String): List<String> =
            selector.split(",").filter { it.isNotBlank() }.map { it.trim() }
}

private fun String.between(prefix: Char, suffix: Char) = this.substring(this.indexOfFirst { it == prefix } + 1, this.indexOfFirst { it == suffix })
private fun <E> List<E>.tail(): List<E> = if (this.isEmpty()) throw RuntimeException("Empty list") else this.drop(1)
private fun <E> List<E>.head(): E = this.first()

private fun String.tail(): String = if (this.isEmpty()) throw RuntimeException("Empty string") else this.drop(1)
private fun String.head(): String = this.take(1)
