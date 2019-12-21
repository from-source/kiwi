package io.from.source.kiwi.json

internal fun Char?.whitespace(): Boolean = this != null && this.isWhitespace()
internal fun Char?.openBracket(): Boolean = '{' == this
internal fun Char?.closeBracket(): Boolean = '}' == this
internal fun Char?.isNull(): Boolean = null == this
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
