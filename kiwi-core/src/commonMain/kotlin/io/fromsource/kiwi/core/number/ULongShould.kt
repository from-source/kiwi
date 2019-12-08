package io.fromsource.kiwi.core.number

import io.fromsource.kiwi.core.BeAny
import io.fromsource.kiwi.core.BeComparable
import io.fromsource.kiwi.core.BeEqual
import io.fromsource.kiwi.core.assert

@ExperimentalUnsignedTypes
class ULongShould(private val actual: ULong) :
        BeAny<ULong, ULongShould>,
        BeEqual<ULong, ULongShould>,
        BeComparable<ULong, ULongShould> {

    override fun actual(): ULong = actual

    fun beZero(): ULongShould = beEqual(ULong.MIN_VALUE)

    fun bePositive(): ULongShould = beGreaterThan(ULong.MIN_VALUE)

    fun beNegative(): ULongShould {
        assert(false) { "ULong can not be negative" }
        return this
    }

    infix fun beLessThan(expected: UByte): ULongShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: UByte): ULongShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beLessThan(expected: UShort): ULongShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: UInt): ULongShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: UShort): ULongShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: UInt): ULongShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }
}