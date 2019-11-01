package io.github.fromsource.kiwi.core.collection

import io.github.fromsource.kiwi.core.BeEqual

open class MapShould<K, V>(private val actual: Map<K, V>) : BeEqual<Map<K, V>, MapShould<K, V>> {
    override fun actual(): Map<K, V> = actual

    open fun beEmpty(): MapShould<K, V> {
        val message = "$actual should be empty"
        assert(actual.isEmpty()) { message }
        return this
    }

    open infix fun haveSize(size: Int): MapShould<K, V> {
        val hasSize = actual.size == size
        val message = "$actual should have size $size"
        assert(hasSize) { message }
        return this
    }

    open infix fun containKey(key: K): MapShould<K, V> {
        val contains = actual.containsKey(key)
        val message = "$actual should contain key '$key'"
        assert(contains) { message }
        return this
    }

    open infix fun containValue(value: V): MapShould<K, V> {
        val contains = actual.containsValue(value)
        val message = "$actual should contain value '$value'"
        assert(contains) { message }
        return this
    }

    open infix fun matchAny(predicate: (Map.Entry<K, V>) -> Boolean): MapShould<K, V> {
        val matchAny = actual.any(predicate)
        val message = "$actual should match any predicate"
        assert(matchAny) { message }
        return this
    }

    open infix fun matchAll(predicate: (Map.Entry<K, V>) -> Boolean): MapShould<K, V> {
        val matchAll = actual.any(predicate)
        val message = "$actual should match all predicate"
        assert(matchAll) { message }
        return this
    }
}