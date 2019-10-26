package io.github.fromsource.kiwi.core.collection

open class CollectionShould<T, R : CollectionShould<T, R>>(open val actual: Collection<T>) {

    open infix fun haveSize(size: Int): R {
        val hasSize = actual.size == size
        val message = "Expected size: $size, actual: ${actual.size}"
        assert(hasSize) { -> message }
        return this as R
    }

    open infix fun contain(element: T): R {

        return this as R
    }
//
//    open fun containAll(vararg  elements: T): R {
//        return this as R
//    }

    open fun beEmpty(): R {
        return this as R
    }
}


open class ListShould<T>(override val actual: List<T>) : CollectionShould<T, ListShould<T>>(actual) {

//    open infix fun have1st(element: T): ListShould<T> {
//        return this
//    }
//
//    open infix fun have2nd(element: T): ListShould<T> {
//        return this
//    }

//    open fun first(): T {
//        return actual.first()
//    }
}
