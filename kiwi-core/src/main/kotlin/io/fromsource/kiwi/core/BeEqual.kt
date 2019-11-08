package io.fromsource.kiwi.core

interface BeEqual<T, R : io.fromsource.kiwi.core.BeEqual<T, R>> {
    fun actual(): T

    infix fun beEqual(expected: T): R {
        val message = "${actual()} should == $expected"
        assert(actual() == expected) { message }
        return this as R
    }
}