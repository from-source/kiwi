package io.fromsource.kiwi.core

interface BeEqual<T, R : BeEqual<T, R>> {
    fun actual(): T

    infix fun beEqual(expected: T): R {
        val message = "${actual()} should == $expected"
        assert(actual() == expected) { message }
        return this as R
    }
}