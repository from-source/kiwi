package io.fromsource.kiwi.core.number

import io.fromsource.kiwi.core.BeAny
import io.fromsource.kiwi.core.BeComparable
import io.fromsource.kiwi.core.BeEqual
import io.fromsource.kiwi.core.assert

class UIntShould(private val actual: UInt) :
        BeAny<UInt, UIntShould>,
        BeEqual<UInt, UIntShould>,
        BeComparable<UInt, UIntShould> {

    override fun actual(): UInt = actual

    fun beZero(): UIntShould = beEqual(UInt.MIN_VALUE)

    fun bePositive(): UIntShould = beGreaterThan(UInt.MIN_VALUE)

    fun beNegative(): UIntShould {
        assert(false) { "UInt can not be negative" }
        return this
    }

    infix fun beLessThan(expected: UByte): UIntShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: UByte): UIntShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beLessThan(expected: UShort): UIntShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: ULong): UIntShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: UShort): UIntShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: ULong): UIntShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }
}