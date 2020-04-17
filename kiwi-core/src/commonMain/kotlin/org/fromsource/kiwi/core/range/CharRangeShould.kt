package org.fromsource.kiwi.core.range

import org.fromsource.kiwi.core.BeAny
import org.fromsource.kiwi.core.assert

class CharRangeShould(private val actual: CharRange) : BeAny<CharRange, CharRangeShould> {

    override fun actual(): CharRange = actual

    infix fun startWith(expected: Char): CharRangeShould {
        val message = "${actual()} should start with $expected"
        assert(actual.first == expected) { message }
        return this
    }

    infix fun notStartWith(expected: Char): CharRangeShould {
        val message = "${actual()} should not start with $expected"
        assert(actual.first != expected) { message }
        return this
    }

    infix fun endWith(expected: Char): CharRangeShould {
        val message = "${actual()} should end with $expected"
        assert(actual.last == expected) { message }
        return this
    }

    infix fun notEndWith(expected: Char): CharRangeShould {
        val message = "${actual()} should not end with $expected"
        assert(actual.last != expected) { message }
        return this
    }

    infix fun contain(expected: Char): CharRangeShould {
        val message = "${actual()} should contain $expected"
        assert(actual.contains(expected)) { message }
        return this
    }

    infix fun notContain(expected: Char): CharRangeShould {
        val message = "${actual()} should not contain $expected"
        assert(!actual.contains(expected)) { message }
        return this
    }

    fun beEmpty(): CharRangeShould {
        val message = "${actual()} should be empty"
        assert(actual.isEmpty()) { message }
        return this
    }

    fun notBeEmpty(): CharRangeShould {
        val message = "${actual()} should not be empty"
        assert(!actual.isEmpty()) { message }
        return this
    }

}