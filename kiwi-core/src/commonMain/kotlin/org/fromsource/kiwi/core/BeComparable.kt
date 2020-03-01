package org.fromsource.kiwi.core

@Suppress("UNCHECKED_CAST")
interface BeComparable<T : Comparable<T>, R : BeComparable<T, R>> {
    fun actual(): T

    infix fun beLessThan(expected: T): R {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this as R
    }

    infix fun beLessOrEqualThan(expected: T): R {
        val message = "${actual()} should be <= $expected"
        assert(actual() <= expected) { message }
        return this as R
    }

    infix fun beGreaterThan(expected: T): R {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this as R
    }

    infix fun beGreaterOrEqualThan(expected: T): R {
        val message = "${actual()} should be >= $expected"
        assert(actual() >= expected) { message }
        return this as R
    }

    fun beBetween(lower: T, upper: T): R {
        val message = "${actual()} should be between ($lower .. $upper)"
        assert(lower < actual() && actual() < upper) { message }
        return this as R
    }
}