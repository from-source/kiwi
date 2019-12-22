package io.from.source.kiwi.json

internal fun Char?.whitespace(): Boolean = this != null && this.isWhitespace()
internal fun Char?.openBracket(): Boolean = '{' == this
internal fun Char?.closeBracket(): Boolean = '}' == this
internal fun Char?.isNull(): Boolean = null == this
internal fun Char?.openArray(): Boolean = '[' == this
internal fun Char?.quotation(): Boolean = '"' == this
internal fun Char?.coma(): Boolean = ',' == this
internal fun Char?.colon(): Boolean = ':' == this
internal fun Char?.boolStart(): Boolean = 't' == this || 'f' == this

internal fun String.toCharList(): List<Char> = this.toCharArray().toList()

internal fun List<Char>.split(token: Char, limit: Int): Pair<List<Char>, List<Char>> {
    val stopCondition: (Char?) -> Boolean = { it != token }

    tailrec fun take(prefix: List<Char>, suffix: List<Char>, upperLimit: Int): Pair<List<Char>, List<Char>> {
        if (upperLimit == 0) {
            return Pair(prefix, suffix)
        }
        val newPrefix = suffix.takeWhile(stopCondition)
        val newSuffix = suffix.dropWhile(stopCondition)

        if (stopCondition(newSuffix.firstOrNull())) {
            return take(prefix + newPrefix, emptyList(), 0)
        }

        return take(prefix + newPrefix.plus(token), newSuffix.drop(1), upperLimit - 1)
    }

    return when {
        limit > 0 -> take(emptyList(), this, limit)
        else -> Pair(this, emptyList())
    }
}

internal fun List<Char>.nextString(): Pair<String, List<Char>> {
    val splited = this.split('"', limit = 2)
    val string = splited.first
            .take(splited.first.size - 1)
            .drop(1)
            .joinToString(separator = "")
    val rest = splited.second
    return Pair(string, rest)
}

internal fun List<Char>.nextBoolean(): Pair<Boolean, List<Char>> {
    if (this.take(5).joinToString(separator = "") == "false") {
        return Pair(false, this.drop(5))
    } else if (this.take(4).joinToString(separator = "") == "true") {
        return Pair(true, this.drop(5))
    } else {
        throw TODO()
    }
}