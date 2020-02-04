package io.from.source.kiwi.jsonpath

import io.from.source.kiwi.json.Json
import io.from.source.kiwi.json.JsonArray
import io.from.source.kiwi.json.JsonObject

object JsonPath {
    fun evaluate(json: Json, path: String): List<Json> {
        return if (path == "$") {
            arrayListOf(json)
        } else if (path == "$..") {
            recursive(json, emptyList())
        } else {
            val paths = path.drop(1).split(".").filter { it.isNotBlank() }
            val result: Json? = traverse(paths, json)
            arrayListOf(result).filterNotNull()
        }
    }

    private tailrec fun traverse(path: List<String>, json: Json?): Json? {
        if (path.isEmpty()) {
            return json
        }
        if (json == null) {
            return json
        }
        val head = path.head()

        val next = when (json) {
            is JsonObject -> json.value[head]
            else -> TODO()
        }
        return traverse(path.tail(), next)
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