package org.fromsource.kiwi.core.range

import org.fromsource.kiwi.core.BeAny
import org.fromsource.kiwi.core.assert

class IntRangeShould(private val actual: IntRange) : BeAny<IntRange, IntRangeShould> {

    override fun actual(): IntRange = actual

    infix fun startWith(expected: Int): IntRangeShould {
        val message = "${actual()} should start with $expected"
        assert(actual.first == expected) { message }
        return this
    }

    infix fun notStartWith(expected: Int): IntRangeShould {
        val message = "${actual()} should not start with $expected"
        assert(actual.first != expected) { message }
        return this
    }

    infix fun endWith(expected: Int): IntRangeShould {
        val message = "${actual()} should end with $expected"
        assert(actual.last == expected) { message }
        return this
    }

    infix fun notEndWith(expected: Int): IntRangeShould {
        val message = "${actual()} should not end with $expected"
        assert(actual.last != expected) { message }
        return this
    }

    infix fun contain(expected: Int): IntRangeShould {
        val message = "${actual()} should contain $expected"
        assert(actual.contains(expected)) { message }
        return this
    }

    infix fun notContain(expected: Int): IntRangeShould {
        val message = "${actual()} should not contain $expected"
        assert(!actual.contains(expected)) { message }
        return this
    }

    fun beEmpty(): IntRangeShould {
        val message = "${actual()} should be empty"
        assert(actual.isEmpty()) { message }
        return this
    }

    fun notBeEmpty(): IntRangeShould {
        val message = "${actual()} should not be empty"
        assert(!actual.isEmpty()) { message }
        return this
    }

    fun overlap(expected: IntRange): IntRangeShould {
        val message = "${actual()} should overlap $expected"
        assert(actual.intersect(expected).isNotEmpty()) { message }
        return this
    }
}