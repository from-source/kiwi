package io.from.source.kiwi.json

class JsonParser {
    fun parse(json: String): Json {
        val tokens = json.toCharArray().dropWhile { it.isWhitespace() }
        return parse(tokens).json
    }

    private fun parse(tokens: List<Char>): ParsingCxt {
        val token = tokens.firstOrNull()
        return when {
            token.openBracket() -> startParseObject(tokens.tail(), JsonObject())
            token.openArray() -> startParseArray(tokens.tail(), JsonArray())
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }

    private tailrec fun startParseArray(tokens: List<Char>, json: JsonArray): ParsingCxt {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> startParseArray(tokens.tail(), json)
            token.closeArray() -> ParsingCxt(json, tokens.tail())
            token.isNull() -> throw JsonException("Unexpected end of json")
            else -> {
                val (value, rest) = parseValue(tokens)
                parseArray(rest, json + value)
            }
        }
    }

    private tailrec fun parseArray(tokens: List<Char>, json: JsonArray): ParsingCxt {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> parseArray(tokens.tail(), json)
            token.closeArray() -> ParsingCxt(json, tokens.tail())
            token.isNull() -> throw JsonException("Unexpected end of json")
            token.coma() -> {
                val (value, rest) = parseValue(tokens.tail())
                parseArray(rest, json + value)
            }
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }

    private tailrec fun startParseObject(tokens: List<Char>, json: JsonObject): ParsingCxt {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> startParseObject(tokens.tail(), json)
            token.closeBracket() -> ParsingCxt(json, tokens.tail())
            token.quotation() -> parseNameValue(tokens, json)
            token.isNull() -> throw JsonException("Unexpected end of json")
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }

    private tailrec fun parseObject(tokens: List<Char>, json: JsonObject): ParsingCxt {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> parseObject(tokens.tail(), json)
            token.closeBracket() -> ParsingCxt(json, tokens.tail())
            token.coma() -> parseNameValue(tokens.tail(), json)
            token.isNull() -> throw JsonException("Unexpected end of json")
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }

    private tailrec fun parseNameValue(tokens: List<Char>, json: JsonObject): ParsingCxt {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> parseNameValue(tokens.tail(), json)
            token.quotation() -> {
                val (key, rest) = tokens.nextString()

                val newRest = rest.dropWhile { it.whitespace() }
                val separator = newRest.firstOrNull()

                val valueRest = when {
                    separator.colon() -> newRest.tail()
                    separator.isNull() -> throw JsonException("Unexpected end of json")
                    else -> throw JsonException("Unrecognized character '$separator'")
                }
                val (value, restObject) = parseValue(valueRest)
                return parseObject(restObject, json.set(key, value))
            }
            token.isNull() -> throw JsonException("Unexpected end of json")
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }

    private tailrec fun parseValue(tokens: List<Char>): ParsingCxt {
        val token = tokens.firstOrNull()
        return when {
            token.minus() -> parseNumberValue(tokens, negative = true)
            token.digit() -> parseNumberValue(tokens)
            token.quotation() -> parseStringValue(tokens)
            token.boolStart() -> parseBoolValue(tokens)
            token.whitespace() -> parseValue(tokens.tail())
            token.openBracket() -> startParseObject(tokens.tail(), JsonObject())
            token.openArray() -> startParseArray(tokens.tail(), JsonArray())
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }

    private fun parseStringValue(tokens: List<Char>): ParsingCxt {
        val splited = tokens.split('"', 2)
        val string = splited.first.take(splited.first.size - 1).tail().joinToString(separator = "")
        return ParsingCxt(JsonString(string), splited.second)
    }

    private fun parseBoolValue(tokens: List<Char>): ParsingCxt {
        val (value, restObject) = when {
            tokens.startsWith(TRUE) -> Pair(true, tokens.drop(TRUE.length))
            tokens.startsWith(FALSE) -> Pair(false, tokens.drop(FALSE.length))
            else -> throw JsonException("Unrecognized boolean value")
        }
        return ParsingCxt(JsonBoolean(value), restObject)
    }

    private fun parseNumberValue(tokens: List<Char>, negative: Boolean = false): ParsingCxt {
        if (negative) {
            val (number, rest) = parseNumberValue(tokens.tail(), false)
            return ParsingCxt((number as JsonNumber).negate(), rest)
        }
        val digits = tokens.takeWhile { it.digit() }
        val rest = tokens.drop(digits.size)
        val number = digits.joinToString(separator = "").toLong()
        return ParsingCxt(JsonNumber(number), rest)
    }
}

fun JsonNumber.negate(): JsonNumber {
    val number: Number = when {
        this.value is Long -> -this.value
        this.value is Double -> -this.value
        else -> throw RuntimeException("Unsupported number")
    }
    return JsonNumber(number)
}

data class ParsingCxt(val json: Json, val rest: List<Char>)