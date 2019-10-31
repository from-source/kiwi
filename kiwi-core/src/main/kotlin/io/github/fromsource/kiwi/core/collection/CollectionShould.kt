package io.github.fromsource.kiwi.core.collection

import io.github.fromsource.kiwi.core.BeEqual

open class CollectionShould<T, R : CollectionShould<T, R>>(open val actual: Collection<T>) : BeEqual<Collection<T>, CollectionShould<T, R>> {
    override fun actual(): Collection<T> = actual

    open fun beEmpty(): R {
        val message = "$actual should be empty"
        assert(actual.isEmpty()) { message }
        return this as R
    }

    open infix fun haveSize(size: Int): R {
        val hasSize = actual.size == size
        val message = "Expected size: $size, actual: ${actual.size}"
        assert(hasSize) { message }
        return this as R
    }

    open infix fun contain(element: T): R {
        val contains = actual.contains(element)
        val message = "$actual should contain $element"
        assert(contains) { message }
        return this as R
    }

    open fun contain(vararg elements: T): R {
        elements.forEach {
            val message = "$actual should contain $it"
            assert(actual.contains(it)) { message }
        }
        return this as R
    }

    open infix fun matchAny(predicate: (T) -> Boolean): R {
        val matchAny = actual.any(predicate)
        val message = "$actual should match any predicate"
        assert(matchAny) { message }
        return this as R
    }

    open infix fun matchAll(predicate: (T) -> Boolean): R {
        val matchAll = actual.any(predicate)
        val message = "$actual should match all predicate"
        assert(matchAll) { message }
        return this as R
    }

}

open class ListShould<T>(override val actual: List<T>) : CollectionShould<T, ListShould<T>>(actual) {

    open infix fun have1st(element: T): ListShould<T> {
        val message = "$actual should have first element to be $element"
        assert(actual.isNotEmpty()) { message }
        assert(actual.first() == element) { message }
        return this
    }

    open infix fun have2nd(element: T): ListShould<T> {
        val message = "$actual should have second element to be $element"
        assert(actual.size >= 2) { message }
        assert(actual[1] == element) { message }
        return this
    }

    open fun first(): T {
        val message = "$actual should have first element"
        assert(actual.isNotEmpty()) { message }
        return actual.first()
    }

    fun last(): T {
        val message = "$actual should have last element"
        assert(actual.isNotEmpty()) { message }
        return actual.last()
    }

    infix fun filtered(predicate: (T) -> Boolean): ListShould<T> {
        return ListShould(actual.filter(predicate))
    }
}

open class SetShould<T>(override val actual: Set<T>) : CollectionShould<T, SetShould<T>>(actual)

open class MapShould<K, V>(private val actual: Map<K, V>) : BeEqual<Map<K, V>, MapShould<K, V>> {
    override fun actual(): Map<K, V> = actual
}