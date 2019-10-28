package io.github.fromsource.kiwi.core.number

class IntShould(private val actual: Int) {

    infix fun beEqual(expected: Int): IntShould {
        val message = "$actual should be == $expected"
        assert(actual == expected) { message }
        return this
    }

    infix fun beLessThan(expected: Int): IntShould {
        val message = "$actual should be < $expected"
        assert(actual < expected) { message }
        return this
    }

    infix fun beGreaterThan(expected: Int): IntShould {
        val message = "$actual should be > $expected"
        assert(actual > expected) { message }
        return this
    }
}