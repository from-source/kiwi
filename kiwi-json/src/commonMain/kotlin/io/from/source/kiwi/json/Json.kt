package io.from.source.kiwi.json

sealed class Json

data class JsonObject(val value: Map<String, Json> = mapOf()) : Json() {
    override fun toString(): String =
            value.entries.joinToString(
                    separator = ", ",
                    prefix = "{",
                    postfix = "}",
                    transform = { """"${it.key}": ${it.value}""" })
}

data class JsonArray(val value: List<Json> = arrayListOf()) : Json() {
    override fun toString(): String = value.toString()
}

data class JsonString(val value: String) : Json() {
    override fun toString(): String = """"$value""""
}

data class JsonNumber(val value: Number) : Json() {
    override fun toString(): String = value.toString()
}

data class JsonBoolean(val value: Boolean) : Json() {
    override fun toString(): String = value.toString()
}

object JsonNull : Json() {
    override fun toString(): String = "null"
}