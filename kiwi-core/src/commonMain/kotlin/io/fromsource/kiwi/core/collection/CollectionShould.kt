@file:Suppress("UNCHECKED_CAST")

package io.fromsource.kiwi.core.collection

import io.fromsource.kiwi.core.BeEqual
import io.fromsource.kiwi.core.assert

open class CollectionShould<T, R : CollectionShould<T, R>>(open val actual: Collection<T>) :
    BeEqual<Collection<T>, CollectionShould<T, R>> {
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

    open infix fun <S : Comparable<S>> beSorted(selector: (T) -> S?): R {
        val sorted = actual().sortedBy(selector)
        val message = "$actual should be sorted: $sorted"
        assert(actual.toList() == sorted) { message }
        return this as R
    }
}

fun <T : Comparable<T>, R : CollectionShould<T, R>> CollectionShould<T, R>.beSorted(): R {
    val sorted = actual().sorted()
    val message = "$actual should be sorted: $sorted"
    assert(actual.toList() == sorted) { message }
    return this as R
}

@kotlin.jvm.JvmName("_shouldSumBytes")
infix fun <R : CollectionShould<Byte, R>> CollectionShould<Byte, R>.sumTo(expected: Int): R {
    val sum = actual().sum()
    val message = "$actual should sum to: $expected"
    assert(sum == expected) { message }
    return this as R
}

@kotlin.jvm.JvmName("_shouldSumShorts")
infix fun <R : CollectionShould<Short, R>> CollectionShould<Short, R>.sumTo(expected: Int): R {
    val sum = actual().sum()
    val message = "$actual should sum to: $expected"
    assert(sum == expected) { message }
    return this as R
}

@kotlin.jvm.JvmName("_shouldSumInts")
infix fun <R : CollectionShould<Int, R>> CollectionShould<Int, R>.sumTo(expected: Int): R {
    val sum = actual().sum()
    val message = "$actual should sum to: $expected"
    assert(sum == expected) { message }
    return this as R
}


@kotlin.jvm.JvmName("_shouldSumLongs")
infix fun <R : CollectionShould<Long, R>> CollectionShould<Long, R>.sumTo(expected: Long): R {
    val sum = actual().sum()
    val message = "$actual should sum to: $expected"
    assert(sum == expected) { message }
    return this as R
}


@kotlin.jvm.JvmName("_shouldSumFloats")
infix fun <R : CollectionShould<Float, R>> CollectionShould<Float, R>.sumTo(expected: Float): R {
    val sum = actual().sum()
    val message = "$actual should sum to: $expected"
    assert(sum == expected) { message }
    return this as R
}


@kotlin.jvm.JvmName("_shouldSumDoubles")
infix fun <R : CollectionShould<Double, R>> CollectionShould<Double, R>.sumTo(expected: Double): R {
    val sum = actual().sum()
    val message = "$actual should sum to: $expected"
    assert(sum == expected) { message }
    return this as R
}