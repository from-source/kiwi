package io.fromsource.kiwi.core.collection

import io.fromsource.kiwi.core.assert

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