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


    /**
     * Alias for beEqual, just allows to use more native DSL language
     */
    infix fun be(expected: T): R = beEqual(expected)

    /**
     * Alias for notBeEqual, just allows to use more native DSL language
     */
    infix fun notBe(expected: T): R = notBeEqual(expected)

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

    infix fun haveHashCodeEqualTo(expected: Int): R {
        val message = "${actual()} should have hashCode equal $expected, but has ${actual().hashCode()}"
        assert(actual().hashCode() == expected) { message }
        return this as R
    }

    infix fun notHaveHashCodeEqualTo(expected: Int): R {
        val message = "${actual()} should not have hashCode equal $expected, but has ${actual().hashCode()}"
        assert(actual().hashCode() != expected) { message }
        return this as R
    }
}