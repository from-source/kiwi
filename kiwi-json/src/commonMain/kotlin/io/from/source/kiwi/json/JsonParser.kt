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
            token.quotation() -> parseNameValue(tokens, json)
            token.isNull() -> throw JsonException("Unexpected end of json")
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }

    private tailrec fun inParseObject(tokens: List<Char>, json: JsonObject): Json {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> inParseObject(tokens.drop(1), json)
            token.closeBracket() -> json
            token.coma() -> parseNameValue(tokens.drop(1), json)
            token.isNull() -> throw JsonException("Unexpected end of json")
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }

    private tailrec fun parseNameValue(tokens: List<Char>, json: JsonObject): Json {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> parseNameValue(tokens.drop(1), json)
            token.quotation() -> {
                val (key, rest) = tokens.nextString()

                val newRest = rest.dropWhile { it.whitespace() }
                val separator = newRest.firstOrNull()

                val valueRest = when {
                    separator.colon() -> newRest.drop(1)
                    separator.isNull() -> throw JsonException("Unexpected end of json")
                    else -> throw JsonException("Unrecognized character '$separator'")
                }
                val (value, restObject) = parseValue(valueRest)
                return inParseObject(restObject, json.set(key, value))
            }
            token.isNull() -> throw JsonException("Unexpected end of json")
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }

    private tailrec fun parseValue(tokens: List<Char>): Pair<Json, List<Char>> {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> parseValue(tokens.drop(1))
            token.quotation() -> {
                val splited = tokens.split('"', 2)
                val string = splited.first.take(splited.first.size - 1).drop(1).joinToString(separator = "")
                return Pair(JsonString(string), splited.second)
            }
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }
}

