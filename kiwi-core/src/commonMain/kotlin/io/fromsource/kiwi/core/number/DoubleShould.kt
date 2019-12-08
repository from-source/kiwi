package io.fromsource.kiwi.core.number

import io.fromsource.kiwi.core.BeAny
import io.fromsource.kiwi.core.BeComparable
import io.fromsource.kiwi.core.BeEqual
import io.fromsource.kiwi.core.assert

class DoubleShould(private val actual: Double) :
        BeAny<Double, DoubleShould>,
        BeEqual<Double, DoubleShould>,
        BeComparable<Double, DoubleShould> {

    override fun actual(): Double = actual

    fun bePositive(): DoubleShould = beGreaterThan(0)

    fun beNegative(): DoubleShould = beLessThan(0)

    fun beZero(): DoubleShould = beEqual(0.0)

    infix fun beLessThan(expected: Byte): DoubleShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Short): DoubleShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Int): DoubleShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Long): DoubleShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Float): DoubleShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Byte): DoubleShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Short): DoubleShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Int): DoubleShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Long): DoubleShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Float): DoubleShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }
}