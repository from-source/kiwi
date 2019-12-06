package io.fromsource.kiwi.core

@Suppress("UNCHECKED_CAST")
interface BeEqual<T, R : BeEqual<T, R>> {
    fun actual(): T

    infix fun beEqual(expected: T): R {
        val message = "${actual()} should == $expected"
        assert(actual() == expected) { message }
        return this as R
    }

    infix fun notBeEqual(expected: T): R {
        val message = "${actual()} should != $expected"
        assert(actual() != expected) { message }
        return this as R
    }

    infix fun beTheSame(expected: T): R {
        val message = "${actual()} should be the same as $expected"
        assert(actual() === expected) { message }
        return this as R
    }

    infix fun notBeTheSame(expected: T): R {
        val message = "${actual()} should not be the same as $expected"
        assert(actual() !== expected) { message }
        return this as R
    }
}