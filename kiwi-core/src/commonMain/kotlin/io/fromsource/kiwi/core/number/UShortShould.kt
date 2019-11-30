package io.fromsource.kiwi.core.number

import io.fromsource.kiwi.core.BeComparable
import io.fromsource.kiwi.core.BeEqual
import io.fromsource.kiwi.core.assert

@ExperimentalUnsignedTypes
class UShortShould(private val actual: UShort) : BeEqual<UShort, UShortShould>, BeComparable<UShort, UShortShould> {
    override fun actual(): UShort = actual

    fun beZero(): UShortShould = beEqual(UShort.MIN_VALUE)

    fun bePositive(): UShortShould = beGreaterThan(UShort.MIN_VALUE)

    fun beNegative(): UShortShould {
        assert(false) { "UShort can not be negative" }
        return this
    }

    infix fun beLessThan(expected: UByte): UShortShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: UByte): UShortShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beLessThan(expected: UInt): UShortShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: ULong): UShortShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: UInt): UShortShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: ULong): UShortShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }
}