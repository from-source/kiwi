package io.from.source.kiwi.json

sealed class Json {
    companion object {
        fun parse(json: String): Json = JsonParser().parse(json)
    }
}

data class JsonObject(private val value: Map<String, Json> = mapOf()) : Json() {
    operator fun plus(jsonObject: JsonObject) = JsonObject(value + jsonObject.value)

    fun set(key: String, json: Json) = JsonObject(value + Pair(key, json))

    fun values(): List<Json> = value.values.toList()

    fun value(key: String): Json? = value[key]

    override fun toString(): String =
            value.entries.joinToString(
                    separator = ", ",
                    prefix = "{",
                    postfix = "}",
                    transform = { """"${it.key}": ${it.value}""" })
}

data class JsonArray(private val value: List<Json> = arrayListOf()) : Json() {
    operator fun plus(json: Json) = JsonArray(value + json)

    override fun toString(): String = value.toString()

    fun values(): List<Json> = value

    fun size(): Int = value.size
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