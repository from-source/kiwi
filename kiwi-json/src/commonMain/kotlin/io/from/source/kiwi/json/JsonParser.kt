package io.from.source.kiwi.json

class JsonParser {
    fun parse(json: String): Json {
        val tokens = json.toCharArray().dropWhile { it.isWhitespace() }
        return parse(tokens, JsonObject())
    }

    private fun parse(tokens: List<Char>, json: JsonObject): Json {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> parse(tokens.drop(1), json)
            token.openBracket() -> parseObject(tokens.drop(1), json)
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }

    private fun parseObject(tokens: List<Char>, json: JsonObject): Json {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> parseObject(tokens.drop(1), json)
            token.closeBracket() -> json
            token.isNull() -> throw JsonException("Unexpected end of json")
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }
}
