package io.from.source.kiwi.json

internal const val TRUE = true.toString()
internal const val FALSE = false.toString()

internal fun Char?.whitespace(): Boolean = this != null && this.isWhitespace()
internal fun Char?.openObject(): Boolean = '{' == this
internal fun Char?.closeObject(): Boolean = '}' == this
internal fun Char?.isNull(): Boolean = null == this
internal fun Char?.openArray(): Boolean = '[' == this
internal fun Char?.closeArray(): Boolean = ']' == this
internal fun Char?.quotation(): Boolean = '"' == this
internal fun Char?.comma(): Boolean = ',' == this
internal fun Char?.colon(): Boolean = ':' == this
internal fun Char?.boolStart(): Boolean = 't' == this || 'f' == this
internal fun Char?.digit(): Boolean = this in ('0'..'9')
internal fun Char?.fraction(): Boolean = '.' == this
internal fun Char?.plus(): Boolean = '+' == this
internal fun Char?.minus(): Boolean = '-' == this
internal fun Char?.startNull(): Boolean = 'n' == this
internal fun Char?.expo(): Boolean = 'e' == this

internal fun String.toCharList(): List<Char> = this.toCharArray().toList()

internal fun List<Char>.split(token: Char, limit: Int): Pair<List<Char>, List<Char>> {
    val stopCondition: (Char?) -> Boolean = { it != token }
    val escape: (Char?) -> Boolean = { it == '\\' }


    tailrec fun take(prefix: List<Char>, suffix: List<Char>, upperLimit: Int): Pair<List<Char>, List<Char>> {
        if (upperLimit == 0) {
            return Pair(prefix, suffix)
        }
        val newPrefix = suffix.takeWhile(stopCondition)
        val newSuffix = suffix.dropWhile(stopCondition)

        if (stopCondition(newSuffix.firstOrNull())) {
            return take(prefix + newPrefix, emptyList(), 0)
        }

        if(escape(newPrefix.lastOrNull())) {
            return take(prefix + newPrefix.plus(token), newSuffix.tail(), upperLimit)
        } else {
            return take(prefix + newPrefix.plus(token), newSuffix.tail(), upperLimit - 1)
        }
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
            .tail()
            .joinToString(separator = "")
    val rest = splited.second
    return Pair(string, rest)
}

internal fun List<Char>.startsWith(string: String): Boolean = this.take(string.length) == string.toCharList()

internal fun <T> List<T>.tail() = this.drop(1)