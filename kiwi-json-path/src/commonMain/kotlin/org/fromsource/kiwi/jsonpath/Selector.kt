package org.fromsource.kiwi.jsonpath

sealed class Selector {
    companion object Factory {
        private const val slice = ':'
        private const val all = '*'
        private const val comma = ","

        fun create(selector: String): Selector {
            return when {
                isSingleIndex(selector) -> SingleIndex(selector.trim().toInt())
                isMultipleIndexes(selector) -> MultipleIndex(indexes(selector))
                isSlice(selector) -> Slice(selector)
                all(selector) -> All
                else -> NoOp
            }
        }

        private fun indexes(selector: String): List<Int> =
                selector.split(comma).filter { isSingleIndex(it) }.map { it.trim().toInt() }

        private fun isMultipleIndexes(selector: String): Boolean =
                selector.split(comma).filter { it.isNotBlank() }.map { it.trim() }.all { isSingleIndex(it) }

        private fun isSingleIndex(selector: String): Boolean =
                selector.trim().toIntOrNull()?.let { true } ?: false

        private fun isSlice(selector: String): Boolean =
                selector.startsWith(slice) && isSingleIndex(selector.tail())

        private fun all(selector: String): Boolean =
                all.toString() == selector.trim()
    }
}

data class SingleIndex(private val index: Int) : Selector() {
    fun value(): Int = index
}

data class MultipleIndex(private val indexes: List<Int>) : Selector() {
    fun indexes(): List<SingleIndex> = indexes.map { SingleIndex(it) }
}

data class Slice(private val expression: String) : Selector() {
    fun indexes(): List<SingleIndex> {
        val last = expression.tail().toInt() - 1
        return (0..last).map { SingleIndex(it) }
    }
}

object NoOp : Selector()

object All : Selector()