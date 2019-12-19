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
            else -> TODO()
        }
    }

    private fun parseObject(tokens: List<Char>, json: JsonObject): Json {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> parseObject(tokens.drop(1), json)
            token.closeBracket() -> json
            token == null -> throw JsonException("Unexpected end of json")
            else -> TODO()
        }
    }
}

private fun Char?.whitespace(): Boolean = this != null && this.isWhitespace()
private fun Char?.openBracket(): Boolean = '{' == this
private fun Char?.closeBracket(): Boolean = '}' == this
