package io.from.source.kiwi.jsonpath

import io.from.source.kiwi.json.Json
import io.from.source.kiwi.json.JsonObject

object JsonPath {
    fun evaluate(json: Json, path: String): List<Json> {
        return if (path == "$") {
            arrayListOf(json)
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
}

private fun <E> List<E>.tail(): List<E> = if (this.isEmpty()) throw RuntimeException("Empty list") else this.drop(1)
private fun <E> List<E>.head(): E = this.first()
