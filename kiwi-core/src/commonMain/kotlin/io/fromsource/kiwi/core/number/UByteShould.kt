package io.fromsource.kiwi.core.number

import io.fromsource.kiwi.core.BeComparable
import io.fromsource.kiwi.core.BeEqual
import io.fromsource.kiwi.core.assert
import kotlin.UByte.Companion.MIN_VALUE

@ExperimentalUnsignedTypes
class UByteShould(private val actual: UByte) : BeEqual<UByte, UByteShould>, BeComparable<UByte, UByteShould> {
    override fun actual(): UByte = actual

    fun beZero(): UByteShould = beEqual(MIN_VALUE)

    fun bePositive(): UByteShould = beGreaterThan(MIN_VALUE)

    fun beNegative(): UByteShould {
        assert(false) { "UByte can not be negative" }
        return this
    }

    infix fun beLessThan(expected: UShort): UByteShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: UInt): UByteShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: ULong): UByteShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: UShort): UByteShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: UInt): UByteShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: ULong): UByteShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }
}