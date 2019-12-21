package io.from.source.kiwi.json

class JsonParser {
    fun parse(json: String): Json {
        val tokens = json.toCharArray().dropWhile { it.isWhitespace() }
        return parse(tokens, JsonObject())
    }

    private tailrec fun parse(tokens: List<Char>, json: JsonObject): Json {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> parse(tokens.drop(1), json)
            token.openBracket() -> parseObject(tokens.drop(1), json)
            token.openArray() -> TODO()
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }

    private tailrec fun parseObject(tokens: List<Char>, json: JsonObject): Json {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> parseObject(tokens.drop(1), json)
            token.closeBracket() -> json
            token == '"' -> {
                val (key, restValue) = parseString(tokens)
                val (value, restObject) = parseValue(restValue)
                return parseObject(restObject, json.set(key, value))
            }
            token.isNull() -> throw JsonException("Unexpected end of json")
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }

    private fun parseString(tokens: List<Char>): Pair<String, List<Char>> {
        val splited = tokens.split('"', limit = 2)
        val string = splited.first.joinToString(separator = "")
        val noQuotes = string.substring(1, string.length - 1)
        val rest = splited.second
        return Pair(noQuotes, rest)
    }

    private fun parseValue(tokens: List<Char>): Pair<Json, List<Char>> {
        val splited = tokens.drop(1).split('"', limit = 2)
        val string = splited.first.joinToString(separator = "")
        val noQuotes = string.substring(1, string.length - 1)
        val rest = splited.second
        return Pair(JsonString(noQuotes), rest)
    }
}
