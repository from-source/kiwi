package org.fromsource.kiwi.core.number

import org.fromsource.kiwi.core.BeAny
import org.fromsource.kiwi.core.BeComparable
import org.fromsource.kiwi.core.BeEqual
import org.fromsource.kiwi.core.assert

class LongShould(private val actual: Long) :
        BeAny<Long, LongShould>,
        BeEqual<Long, LongShould>,
        BeComparable<Long, LongShould> {

    override fun actual(): Long = actual

    fun bePositive(): LongShould = beGreaterThan(0)

    fun beNegative(): LongShould = beLessThan(0)

    fun beZero(): LongShould = beEqual(0L)

    infix fun beLessThan(expected: Byte): LongShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Short): LongShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Int): LongShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Float): LongShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beLessThan(expected: Double): LongShould {
        val message = "${actual()} should be < $expected"
        assert(actual() < expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Byte): LongShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Short): LongShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Int): LongShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Float): LongShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Double): LongShould {
        val message = "${actual()} should be > $expected"
        assert(actual() > expected) { message }
        return this
    }
}