package io.from.source.kiwi.json

class JsonParser {
    fun parse(value: String): Json {
        val tokens = value.toCharArray().dropWhile { it.isWhitespace() }
        val (json, rest) = parse(tokens)
        val trimmed = rest.dropWhile { it.whitespace() }
        if (trimmed.isEmpty()) {
            return json
        }
        throw JsonException("Unexpected character '${trimmed.first()}'")

    }

    private fun parse(tokens: List<Char>): ParsingCxt {
        val token = tokens.firstOrNull()
        return when {
            token.openObject() -> startParseObject(JsonObject(), tokens.tail())
            token.openArray() -> startParseArray(JsonArray(), tokens.tail())
            else -> parseValue(tokens)
        }
    }

    private tailrec fun startParseArray(json: JsonArray, tokens: List<Char>): ParsingCxt {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> startParseArray(json, tokens.tail())
            token.closeArray() -> ParsingCxt(json, tokens.tail())
            token.isNull() -> throw JsonException("Unexpected end of json")
            else -> {
                val (value, rest) = parseValue(tokens)
                parseArray(json + value, rest)
            }
        }
    }

    private tailrec fun parseArray(json: JsonArray, tokens: List<Char>): ParsingCxt {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> parseArray(json, tokens.tail())
            token.closeArray() -> ParsingCxt(json, tokens.tail())
            token.isNull() -> throw JsonException("Unexpected end of json")
            token.comma() -> {
                val (value, rest) = parseValue(tokens.tail())
                parseArray(json + value, rest)
            }
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }

    private tailrec fun startParseObject(json: JsonObject, tokens: List<Char>): ParsingCxt {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> startParseObject(json, tokens.tail())
            token.closeObject() -> ParsingCxt(json, tokens.tail())
            token.quotation() -> parseNameValue(json, tokens)
            token.isNull() -> throw JsonException("Unexpected end of json")
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }

    private tailrec fun parseObject(json: JsonObject, tokens: List<Char>): ParsingCxt {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> parseObject(json, tokens.tail())
            token.closeObject() -> ParsingCxt(json, tokens.tail())
            token.comma() -> parseNameValue(json, tokens.tail())
            token.isNull() -> throw JsonException("Unexpected end of json")
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }

    private tailrec fun parseNameValue(json: JsonObject, tokens: List<Char>): ParsingCxt {
        val token = tokens.firstOrNull()
        return when {
            token.whitespace() -> parseNameValue(json, tokens.tail())
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
                return parseObject(json.set(key, value), restObject)
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
            token.openObject() -> startParseObject(JsonObject(), tokens.tail())
            token.openArray() -> startParseArray(JsonArray(), tokens.tail())
            token.startNull() -> parseNull(tokens)
            else -> throw JsonException("Unrecognized character '$token'")
        }
    }

    private fun parseNull(tokens: List<Char>) = when {
        tokens.startsWith(NULL) -> ParsingCxt(JsonNull, tokens.drop(NULL.length))
        else -> {
            val unknown = tokens.takeWhile { !it.isWhitespace() }.joinToString(separator = "")
            throw JsonException("Unrecognized value '${unknown}'")
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
        val number = tokens.takeWhile { it.digit() || it.fraction() || it.minus() || it.plus() || it.expo() }.joinToString(separator = "")
        val long = number.toLongOrNull()
        val double = number.toDoubleOrNull()
        return when {
            long != null -> ParsingCxt(JsonNumber(long), tokens.drop(number.length))
            double != null -> ParsingCxt(JsonNumber(double), tokens.drop(number.length))
            else -> throw JsonException("Can not parse number $number")
        }
    }

    companion object {
        const val NULL = "null"
    }
}

data class ParsingCxt(val json: Json, val rest: List<Char>)