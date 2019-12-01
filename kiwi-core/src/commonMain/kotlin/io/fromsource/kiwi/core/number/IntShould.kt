package io.fromsource.kiwi.core.number

import io.fromsource.kiwi.core.BeComparable
import io.fromsource.kiwi.core.BeEqual
import io.fromsource.kiwi.core.assert

class IntShould(private val actual: Int) : BeEqual<Int, IntShould>, BeComparable<Int, IntShould> {
    override fun actual(): Int = actual

    fun bePositive(): IntShould = beGreaterThan(0)

    fun beNegative(): IntShould = beLessThan(0)

    fun beZero(): IntShould = beEqual(0)

    infix fun beLessThan(expected: Byte): IntShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Short): IntShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Long): IntShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Float): IntShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Double): IntShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Byte): IntShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Short): IntShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Long): IntShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Float): IntShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Double): IntShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }
}